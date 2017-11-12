package com.example.han.gachafriends;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by per6 on 10/20/17.
 */

public class Summon {
    public static final String TAG = "Tagg";
    private Context mContext;
    private BufferedReader nameReader;
    private ImageView image;

    public Summon (Context context)
    {
        mContext = context;
        setupText();
    }

    private void setupText() {
        nameReader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.names)));
    }

    public int totalLines(){
        setupText();
        int i = 0;
        try {
            while(nameReader.readLine() != null){
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public Friend summon()
    {
        setupText();
        int total = totalLines();
        int friend = (int) (Math.random()*total + 1);
        Friend temp = new Friend(friend, mContext);
        Log.d(TAG, temp.getName());
        Toast.makeText(mContext, temp.getName(), Toast.LENGTH_SHORT).show();
        
        return temp;
        //Collection.friends.add(temp);
        //?????
    }
}
