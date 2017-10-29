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
    private Context mContext;
    private BufferedReader reader;
    private static final String TAG = "this tag";
    public Summon (Context context)
    {
        mContext = context;
        setupReader();
    }

    public int totalLines(){
        int linesRead = 0;
        String line;
        try {
            while((line = reader.readLine()) != null){
                linesRead++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesRead;
    }

    /**
     *
     * @param lineNumber Starts at one, because lines start at one.
     * @return
     */
    public String readLine(int lineNumber) {
        String charaName = "";
        for(int i = 0; i < lineNumber; i++){
            try {
                charaName = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return charaName;
    }

    private void setupReader() {
            reader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.names)));
    }
}
