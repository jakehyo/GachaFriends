package com.example.han.gachafriends;

import java.util.Arrays;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by per6 on 10/20/17.
 */

public class Collection {

    private int[] collection;
    private int coin;

    public Collection()
    {
        this.collection = new int[]{3,4,10,13,15,17,20,25};
    }

    public int[] convertSetToArray(Set<String> temp)
    {
        String[] temp2 = temp.toArray(new String[temp.size()]);
        int[] ids = new int[temp2.length];
        for (int i = 0; i < ids.length;i++)
        {
            ids[i] = Integer.parseInt(temp2[i]);
        }
        return ids;
    }

    public ArrayList<Friend> getFriendList(Context context){
        ArrayList<Friend> friendArrayList = new ArrayList<>();
        for(int friendID : collection){
            friendArrayList.add(new Friend(friendID, context));
        }
        return friendArrayList;
    }

    public Friend getFriend(int id, Context context){
        return new Friend(collection[id], context);
    }

    public Set<String> convertArrayToSet(int[] temp) {
        String[] temp2 = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            temp2[i] = temp[i] + "";
        }
        Set<String> ids = new HashSet<String>(Arrays.asList(temp2));
        return ids;
    }



    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void addCoin(int i)
    {
        coin +=i;
    }

    public int[] getCollection() {
        return collection;
    }

    public void setCollection(int[] collection) {
        this.collection = collection;
    }




}
