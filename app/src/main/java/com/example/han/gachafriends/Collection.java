package com.example.han.gachafriends;

import android.content.Context;

import java.util.ArrayList;
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

    public ArrayList<Friend> getFriendList(Context context){
        ArrayList<Friend> friendArrayList = new ArrayList<>();
        for(int friendID : collection){
            friendArrayList.add(new Friend(friendID, context));
        }
        return friendArrayList;
    }

    public Set<String> convertSettoArray(int[] collection)
    {
        //Needs implementation

        Set<String> temp = new HashSet<String>();
        return temp;
        //:L
    }
}
