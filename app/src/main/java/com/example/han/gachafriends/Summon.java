package com.example.han.gachafriends;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by per6 on 10/20/17.
 */

public class Summon {
    public static final String TAG = "Tagg";
    public Context mContext;
    public BufferedReader reader;

    public Summon (Context context)
    {
        mContext = context;
        setupText();
    }

    private void setupText() {
        reader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.names)));
    }

    public String readLineNo(int lineNo) {
        String givenLine = "";
        for(int i = 0; i < lineNo; i++){
            try {
                givenLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return givenLine;
    }

    public int totalLines(){
        int i = 0;
        try {
            while(reader.readLine() != null){
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public void summon()
    {
        int total = totalLines();
        int friend = (int) (Math.random()*total);

        Friend temp = new Friend(friend);
        Collection.friends.add(temp);
        //?????
    }
}
