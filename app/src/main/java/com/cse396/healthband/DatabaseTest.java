package com.cse396.healthband;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    protected static void fillWithWeeklyMock(){
        Random rand = new Random();

        String[] dates = {"01/04/2019 12:16",
                "01/04/2019 13:16",
                "01/04/2019 14:16",
                "02/04/2019 13:22",
                "02/04/2019 14:22",
                "02/04/2019 15:22",
                "03/04/2019 11:54",
                "03/04/2019 12:54",
                "03/04/2019 13:54",
                "04/04/2019 18:32",
                "04/04/2019 19:32",
                "04/04/2019 20:32",
                "05/04/2019 21:12",
                "05/04/2019 22:32",
                "05/04/2019 23:24",
                "06/04/2019 09:00",
                "06/04/2019 10:00",
                "06/04/2019 11:00",
                "07/04/2019 05:02",
                "07/04/2019 06:02",
                "07/04/2019 08:02"};

        List<TaggedPairs<String, Integer>> stepsData = new ArrayList<> ();
        List<TaggedPairs<String, Integer>> flightsClimbedData = new ArrayList<> ();
        List<TaggedPairs<String, Integer>> hearthRateData = new ArrayList<> ();
        for(String d : dates){
            stepsData.add(new TaggedPairs("date", d, "stepCount", rand.nextInt(1000)));
            flightsClimbedData.add(new TaggedPairs("date", d, "climbed", rand.nextInt(20)));
            hearthRateData.add(new TaggedPairs("date", d, "bmp", 60 + rand.nextInt(15)));
        }
        setValues(STEPS, stepsData);
        setValues(FLIGHTS_CLIMBED, flightsClimbedData);
        setValues(HEARTH_RATE, hearthRateData);

        List<TaggedPairs<String, String>> currentMovementData = new ArrayList<> ();
        currentMovementData.add(new TaggedPairs("date", "06/04/2019 19:23", "movement", "Sitting"));
        setValues(CURRENT_MOVEMENT, currentMovementData);

    }
}
