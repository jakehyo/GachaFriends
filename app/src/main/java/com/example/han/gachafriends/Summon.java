package com.example.han.gachafriends;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
        setupText(context);
    }

    private void setupText(Context context) {
        reader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.names)));
    }

    public String readLineNo(int lineNo) throws IOException {
        String givenLine = "";
        for(int i = 0; i < lineNo; i++){
            givenLine = reader.readLine();
        }
        return givenLine;
    }

    public int totalLines() throws IOException{
        int i = 0;
        while(reader.readLine() != null){
            i++;
        }
        return i;
    }
}
