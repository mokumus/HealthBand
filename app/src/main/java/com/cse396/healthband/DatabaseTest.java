package com.cse396.healthband;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseTest is the main class we'll be using to fill the Firebase database
 * with mock data and set/clear that data with its methods.
 * @author Muhammed Okumus
 */
class DatabaseTest {
    static Integer i = 0;
    private static final String STEPS = "steps";
    private static final String HEARTH_RATE = "hearthRate";
    private static final String FLIGHTS_CLIMBED = "flightsClimbed";
    private static final String CURRENT_MOVEMENT = "currentMovement";

    /**
     * @param name child data node name
     * @param data array of TaggedPairs<String, T>
     * @param <T> any object
     * Create/Rewrite all values in the given child to those in the provided data
     */
    protected static <T> void setValues(String name, List<TaggedPairs<String, T>> data){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(name);


        for(TaggedPairs<String, T> entry : data){
            myRef.child(i.toString()).child(entry.getFirstTag()).setValue(entry.getFirstValue());
            myRef.child(i.toString()).child(entry.getSecondTag()).setValue(entry.getSecondValue());
            i++;
        }

        i = 0;
    }

    /**
     * @param name child data node name
     * Deletes the given child from database.
     */
    protected static void clearChild(String name){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(name);
        myRef.removeValue();
    }

    /**
     * Fills the given child in the database with mock data. Will override data if date's
     * are not changed. Only use for testing purposes.
     */
    protected static void fillWithMock(){

        List<TaggedPairs<String, Integer>> stepsData = new ArrayList<> ();
        stepsData.add(new TaggedPairs("date", "06/04/2019 12:46", "stepCount", 150));
        stepsData.add(new TaggedPairs("date", "06/04/2019 13:32", "stepCount", 350));
        stepsData.add(new TaggedPairs("date", "06/04/2019 19:23", "stepCount", 1050));
        setValues(STEPS, stepsData);

        List<TaggedPairs<String, Integer>> flightsClimbedData = new ArrayList<> ();
        flightsClimbedData.add(new TaggedPairs("date", "06/04/2019 12:46", "climbed", 1));
        flightsClimbedData.add(new TaggedPairs("date", "06/04/2019 13:32", "climbed", 5));
        flightsClimbedData.add(new TaggedPairs("date", "06/04/2019 19:23", "climbed", 13));
        setValues(FLIGHTS_CLIMBED, flightsClimbedData);

        List<TaggedPairs<String, Integer>> hearthRateData = new ArrayList<> ();
        hearthRateData.add(new TaggedPairs("date", "06/04/2019 12:46", "bmp", 68));
        hearthRateData.add(new TaggedPairs("date", "06/04/2019 13:32", "bmp", 70));
        hearthRateData.add(new TaggedPairs("date", "06/04/2019 19:23", "bmp", 65));
        setValues(HEARTH_RATE, hearthRateData);

        List<TaggedPairs<String, String>> currentMovementData = new ArrayList<> ();
        currentMovementData.add(new TaggedPairs("date", "06/04/2019 19:23", "movement", "Sitting"));
        setValues(CURRENT_MOVEMENT, currentMovementData);
    }
}
