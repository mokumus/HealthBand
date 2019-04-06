package com.cse396.healthband;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

class DatabaseRead {

    protected static void retrieveValues(String childName){
        //Create an instance of the database and get reference of the child with the given name
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(childName);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<HashMap<String, Object>> values =  (ArrayList<HashMap<String, Object>>)dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + values.get(0).get("date"));
                Log.d(TAG, "Value is: " + values.get(1));
                Log.d(TAG, "Value is: " + values.get(2));

                // Organize values if needed
                // ...
                // Update view according to values
                // ...
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
