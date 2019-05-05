package com.cse396.healthband;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static android.support.constraint.Constraints.TAG;

public class QueryLocal {

    //TODO: Error handling in UI might be needed
    public static Date parseTime(String timeStr){
        Date date = null;
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            // To get the date object from the string just called the
            // parse method and pass the time string to it. This method
            // throws ParseException if the time string is invalid.
            // But remember as we don't pass the date information this
            // date object will represent the 1st of january 1970.
            date = sdf.parse(timeStr);
            //System.out.println("Date and Time: " + date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    protected static void queryByDateRange(String childName, final Date start, final Date end){
        //Create an instance of the database and get reference of the child with the given name
        //TODO: make database and myRef a field so we can close them later if needed

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(childName);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<HashMap<String, Object>> values =  (ArrayList<HashMap<String, Object>>)dataSnapshot.getValue();
                ArrayList<HashMap<String, Object>> querriedResults = new ArrayList();
                for (int i = 0; i < values.size(); i++) {
                    //Log.d(TAG, "Value is: " + values.get(i));
                    //Log.d(TAG, "time is: " + parseTime((String) values.get(i).get("date")));
                    Date tempDate = parseTime((String) values.get(i).get("date"));
                    if (start.before(tempDate) && end.after(tempDate)) {
                        Log.d(TAG, "Value is: " + values.get(i));
                        Log.d(TAG, "time is: " + parseTime((String) values.get(i).get("date")));
                        querriedResults.add(values.get(i));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
