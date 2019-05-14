package com.cse396.healthband;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import static android.support.constraint.Constraints.TAG;
import static com.cse396.healthband.DatabaseTest.clearChild;
import static com.cse396.healthband.DatabaseTest.fillWithTimestampMock;

public class FirebaseActivity extends AppCompatActivity {
    private static final boolean CLEAR = false;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Button readDbButton;
    Button queryButton;


    ArrayList<HashMap<String, Object>> values;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        FirebaseApp.initializeApp(this);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        readDbButton =findViewById(R.id.getDataBtn);
        queryButton=findViewById(R.id.getQueryBtn);

        readDbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(CLEAR)
                    clearAndFill();
                
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("steps");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        values =  (ArrayList<HashMap<String, Object>>)dataSnapshot.getValue();
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        mAdapter = new MyAdapter(values);
                        recyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(layoutManager);

                Date s = parseTime(1557788389928L);
                Date e = parseTime(1557788389952L);

                mAdapter = new MyAdapter(queryByDateRange(s, e));
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    ArrayList<HashMap<String, Object>> queryByDateRange(final Date start, final Date end){
        ArrayList<HashMap<String, Object>> queriedResults = new ArrayList<>();
        for(HashMap<String, Object> e : values){
            Date tempDate = parseTime((long) e.get("timestamp"));
            if (start.before(tempDate) && end.after(tempDate))
                queriedResults.add(e);
        }
        return queriedResults;
    }

    /**
     * Parses unix time to  Java.Date
     * @param timeStamp unix time
     * @return
     */
    public static Date parseTime(long timeStamp){
        java.util.Date date = new java.util.Date(timeStamp*1000);
        return date;
    }


    public static void clearAndFill(){
        clearChild("steps");
        clearChild("hearthRate");
        clearChild("flightsClimbed");
        clearChild("currentMovement");
        fillWithTimestampMock();
    }
}
