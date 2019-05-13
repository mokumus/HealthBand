package com.cse396.healthband;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import static android.support.constraint.Constraints.TAG;
import static com.cse396.healthband.DatabaseTest.clearChild;
import static com.cse396.healthband.DatabaseTest.fillWithTimestampMock;
import static com.cse396.healthband.DatabaseTest.fillWithWeeklyMock;

public class FirebaseActivity extends AppCompatActivity {

    private static final String STEPS = "steps";
    private static final String HEARTH_RATE = "hearthRate";
    private static final String FLIGHTS_CLIMBED = "flightsClimbed";
    private static final String CURRENT_MOVEMENT = "currentMovement";
    private static final boolean CLEAR = false;


    ArrayList<HashMap<String, Object>> values;
    private RecyclerView recyclerView;
    MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    Button getDatasBtn;
    Button getQuerysBtn;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        FirebaseApp.initializeApp(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        getDatasBtn=findViewById(R.id.getDataBtn);
        getQuerysBtn=findViewById(R.id.getQueryBtn);

        getQuerysBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(layoutManager);

                Date s = parseTime(1557788389928L);
                Date e = parseTime(1557788389952L);

                mAdapter = new MyAdapter(queryByDateRange(s, e));
                recyclerView.setAdapter(mAdapter);
            }
        });

        getDatasBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(CLEAR){
                    clearChild(STEPS);
                    clearChild(FLIGHTS_CLIMBED);
                    clearChild(HEARTH_RATE);
                    clearChild(CURRENT_MOVEMENT);
                    fillWithTimestampMock();
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference(STEPS);

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
    }

    ArrayList<HashMap<String, Object>> queryByDateRange(final Date start, final Date end){
        ArrayList<HashMap<String, Object>> querriedResults = new ArrayList<>();
        for(HashMap<String, Object> e : values){
            Date tempDate = parseTime((long) e.get("timestamp"));
            if (start.before(tempDate) && end.after(tempDate))
                querriedResults.add(e);
        }
        return querriedResults;
    }

    public static Date parseTime(long timeStamp){
        java.util.Date date = new java.util.Date(timeStamp*1000);
        return date;
    }
}
