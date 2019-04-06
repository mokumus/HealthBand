package com.cse396.healthband;

import android.util.Pair;

/**
 * @param <U> any object
 * @param <V> any object
 * Class to associate two names with two values.
 */
class TaggedPairs<U, V> {
    public Pair<String, U> tag1;
    public Pair<String, V> tag2;

    public TaggedPairs(String name1, U item1,String name2, V item2){
        this.tag1 = new Pair<>(name1, item1);
        this.tag2 = new Pair<>(name2, item2);
    }


    public String getFirstTag(){ return tag1.first; }

    public String getSecondTag(){ return tag2.first; }

    public U getFirstValue(){ return tag1.second; }

    public V getSecondValue(){ return tag2.second; }


    @Override
    public String toString() {
        return tag1.toString() + tag2.toString();
    }
}
