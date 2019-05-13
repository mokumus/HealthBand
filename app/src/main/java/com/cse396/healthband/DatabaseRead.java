package com.cse396.healthband;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
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

class DatabaseRead {

    static void retrieveValues(String childName){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(childName);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<HashMap<String, Object>> values =  (ArrayList<HashMap<String, Object>>)dataSnapshot.getValue();
                assert values != null;
                for(int i = 0; i < values.size(); i++){
                    Log.d(TAG, "Value is: " + values.get(i));
                }

                // Organize values if needed
                // ...
                // Update view according to values

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public static Date parseTime(String timeStr){
        Date date = null;
        @SuppressLint("SimpleDateFormat") DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            date = sdf.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
