package com.example.han.gachafriends;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by per6 on 10/20/17.
 */

public class Collection {

    private int[] collection;

    public Collection()
    {
        collection = new int[2];
        collection[0] = 3;
        collection[1] = 4;
    }

    public int[] getCollection(){
        return collection;
    }

    public Set<String> convertSettoArray(int[] collection)
    {
        //Needs implementation

        Set<String> temp = new HashSet<String>();
        return temp;
        //:L
    }
}
