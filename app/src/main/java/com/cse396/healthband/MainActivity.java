package com.cse396.healthband;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;
import static com.cse396.healthband.DatabaseRead.retrieveValues;
import static com.cse396.healthband.DatabaseTest.clearChild;
import static com.cse396.healthband.DatabaseTest.fillWithMock;


public class MainActivity extends AppCompatActivity {
    private static final String STEPS = "steps";
    private static final String HEARTH_RATE = "hearthRate";
    private static final String FLIGHTS_CLIMBED = "flightsClimbed";
    private static final String CURRENT_MOVEMENT = "currentMovement";
    private static final boolean CLEAR = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        DatabaseRead dbReader = new DatabaseRead();

        if(CLEAR == true){
            clearChild(STEPS);
            clearChild(FLIGHTS_CLIMBED);
            clearChild(HEARTH_RATE);
            clearChild(CURRENT_MOVEMENT);
        }

        //fillWithMock();

        ArrayList<HashMap<String, Object>> toBeFilled = new ArrayList<>();
        dbReader.retrieveValues(STEPS);
        Log.d(TAG, "toBeFilled: " + toBeFilled);







    }
}
