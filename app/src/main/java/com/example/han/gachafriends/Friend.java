package com.example.han.gachafriends;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by per6 on 10/20/17.
 */

public class Friend {

    private int id;
    private String name;
    private String desc;
    private Context mContext;
    private BufferedReader nameReader;
    //private String imageName;


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

    public int totalLines(){
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

    public String getName() {
        return name;
    }

    public int getImageId(){
        String nameWithUnderscores = "";
        for(int i = 0; i < name.length(); i++){
            String tempLetter = "";
            if(name.substring(i, i+1).equals(" ")){
                tempLetter = "_";
            } else {
                tempLetter = name.substring(i, i+1);
            }
            nameWithUnderscores = nameWithUnderscores + tempLetter;
        }
        String imageName = "@drawable/" + nameWithUnderscores.toLowerCase();
        return mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
    }

    public int getFrameId(){
        String nameWithUnderscores = "";
        for(int i = 0; i < name.length(); i++){
            String tempLetter = "";
            if(name.substring(i, i+1).equals(" ")){
                tempLetter = "_";
            } else {
                tempLetter = name.substring(i, i+1);
            }
            nameWithUnderscores = nameWithUnderscores + tempLetter;
        }
        String imageName = "@drawable/" + nameWithUnderscores.toLowerCase() + "_frame";
        return mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
    }

    @Override
    public String toString() {
        return name;
    }
}
