package com.cse396.healthband;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;
import static com.cse396.healthband.DatabaseRead.parseTime;
import static com.cse396.healthband.DatabaseRead.retrieveValues;
import static com.cse396.healthband.DatabaseTest.clearChild;
import static com.cse396.healthband.DatabaseTest.fillWithMock;
import static com.cse396.healthband.DatabaseTest.fillWithWeeklyMock;
import static com.cse396.healthband.QueryLocal.queryByDateRange;


public class MainActivity extends AppCompatActivity {
    private static final String STEPS = "steps";
    private static final String HEARTH_RATE = "hearthRate";
    private static final String FLIGHTS_CLIMBED = "flightsClimbed";
    private static final String CURRENT_MOVEMENT = "currentMovement";
    private static final boolean CLEAR = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        //We need to declare multiple DatabaseRead instances to listen different fields.
        DatabaseRead dbReader = new DatabaseRead();


        if(CLEAR == true){
            clearChild(STEPS);
            clearChild(FLIGHTS_CLIMBED);
            clearChild(HEARTH_RATE);
            clearChild(CURRENT_MOVEMENT);
            fillWithWeeklyMock();
        }

        //dbReader.retrieveValues(STEPS);
        Date s = parseTime("03/04/2019 00:00");
        Date e = parseTime("06/04/2019 00:00");

        queryByDateRange(STEPS,s,e);

    }
}