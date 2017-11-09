package com.example.han.gachafriends;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by per6 on 10/20/17.
 */

public class Summon {
    public static final String TAG = "Tagg";
    private Context mContext;

    public Summon (Context context)
    {
        mContext = context;
    }


    public Friend summon()
    {
        Friend temp = new Friend(1, mContext);
        int total = temp.totalLines();
        int friend = (int) (Math.random()*total + 1);
        temp = new Friend(friend, mContext);
        Log.d(TAG, temp.getName());
        Toast.makeText(mContext, temp.getName(), Toast.LENGTH_SHORT).show();
        return temp;
        //Collection.friends.add(temp);
        //?????
    }
}
