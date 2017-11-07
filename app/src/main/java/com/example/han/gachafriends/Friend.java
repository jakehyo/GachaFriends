package com.example.han.gachafriends;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by per6 on 10/20/17.
 */

public class Friend {

    private int id;
    private int imgResourceId;
    private String name;
    private String desc;
    private Context mContext;
    private BufferedReader nameReader;

    public Friend(int id, Context context) {
        this.id = id;
        mContext = context;
        setupText();

        name = readLineNo(id, nameReader);
    }

    private void setupText() {
        nameReader = new BufferedReader(new InputStreamReader(mContext.getResources().openRawResource(R.raw.names)));
    }

    public String readLineNo(int lineNo, BufferedReader reader) {
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


    public String getName() {
        return name;
    }

    public int getImageId(){
        String imageName = "@drawable/" + name.toLowerCase();
        return mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
    }

}
