package com.cse396.healthband;

import android.support.annotation.NonNull;
import android.util.Pair;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;
import java.util.HashMap;
import java.util.Map;

/**
 * @param <U> any object
 * @param <V> any object
 * Class to associate two names with two values.
 */
class TaggedPairs<U, V> {

    private HashMap<String, Object> timestamp;
    private Pair<String, U> tag1;
    private Pair<String, V> tag2;

    TaggedPairs(String name1, U item1,String name2, V item2){
        this.tag1 = new Pair<>(name1, item1);
        this.tag2 = new Pair<>(name2, item2);
    }

    @SuppressWarnings("unchecked")
    TaggedPairs(String name1, V item1){
        this.tag1 = new Pair<>("timestamp",(U) ServerValue.TIMESTAMP);
        this.tag2 = new Pair<>(name1, item1);
    }


    public HashMap<String, Object> getTimestampCreated(){
        return timestamp;
    }

    String getFirstTag(){ return tag1.first; }

    String getSecondTag(){ return tag2.first; }

    U getFirstValue(){ return tag1.second; }

    V getSecondValue(){ return tag2.second; }


    @Exclude
    public long getTimestampCreatedLong(){
        return (long)timestamp.get("timestamp");
    }

    @NonNull
    @Override
    public String toString() {
        return tag1.toString() + tag2.toString();
    }
}
