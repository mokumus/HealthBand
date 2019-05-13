package com.cse396.healthband;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import static com.cse396.healthband.DatabaseTest.*;
import static com.cse396.healthband.DatabaseRead.*;


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


        if(CLEAR){
            clearChild(STEPS);
            clearChild(FLIGHTS_CLIMBED);
            clearChild(HEARTH_RATE);
            clearChild(CURRENT_MOVEMENT);
            fillWithTimestampMock();
        }

        retrieveValues(STEPS);
        retrieveValues(HEARTH_RATE);


    }
}