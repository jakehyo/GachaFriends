package com.example.han.gachafriends;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by per6 on 10/20/17.
 */

public class Summon implements Parcelable {
    public static final String TAG = "Tagg";
    private Context mContext;
    private BufferedReader nameReader;
    private ImageView image;
    private String name;

    public Summon (Context context)
    {
        mContext = context;
        setupText();
    }

    public void setupText() {
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
        Friend temp = new Friend(1, mContext);
        int total = temp.totalLines();
        int friend = (int) (Math.random()*(total - 1) + 1);
        temp = new Friend(friend, mContext);
        Log.d(TAG, temp.getName());
        Toast.makeText(mContext,"GET", Toast.LENGTH_SHORT).show();
        name = (temp.getName());
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    protected Summon(Parcel in) {
        mContext = (Context) in.readValue(Context.class.getClassLoader());
        nameReader = (BufferedReader) in.readValue(BufferedReader.class.getClassLoader());
        image = (ImageView) in.readValue(ImageView.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeValue(mContext);
        // Causes crash whenever Activity closes or pauses.
        //dest.writeValue(nameReader);
        dest.writeValue(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Summon> CREATOR = new Parcelable.Creator<Summon>() {
        @Override
        public Summon createFromParcel(Parcel in) {
            return new Summon(in);
        }

        @Override
        public Summon[] newArray(int size) {
            return new Summon[size];
        }
    };

    public void setContext(FragmentActivity context) {
        this.mContext = context;
    }
}
