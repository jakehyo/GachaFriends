package com.example.han.gachafriends;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by per6 on 10/20/17.
 */

public class Collection {

    private int[] collection;

    public Collection()
    {
        this.collection = new int[]{0,1,2};
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

    public Set<String> convertArrayToSet(int[] temp)
    {
        String[] temp2 = new String[temp.length];
        for (int i=0; i < temp.length;i++)
        {
            temp2[i] = temp[i] + "";
        }
        Set<String> ids = new HashSet<String>(Arrays.asList(temp2));
        return ids;
        //magic
    }

    public void addCoin(int i)
    {
        collection[0]+=i;
    }

    public int[] getCollection() {
        return collection;
    }

    public void setCollection(int[] collection) {
        this.collection = collection;
    }




}
