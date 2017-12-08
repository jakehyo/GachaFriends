package com.example.han.gachafriends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;
    private TextView coinText;
    public int coin = 5;
    public static final String TAG = "TIM_DEBUG";
    private Collection collection;
    private Set<String> emptySet = new HashSet<String>();
    private boolean ranConstructor;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setOnClickListeners();

        coinText.setText("Coins: "+ coin);

        // Set up SharedPreference storage
        emptySet.add("0");
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        collection = new Collection();

        int[] bufferSet = collection.convertSetToArray(sharedPref.getStringSet(getString(R.string.collection_key),emptySet));
        int bufferCoin = sharedPref.getInt(getString(R.string.coin_key), 0);
        collection.setCoin(bufferCoin);
        collection.setCollection(bufferSet);



        ranConstructor = true;


        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, new FragmentSummon()).commit();

        //pull the collection ids from shared prefs and instantiate the collection (or maybe in onResume)
    }

    private void setOnClickListeners() {
        homeImageButton.setOnClickListener(this);
        missionImageButton.setOnClickListener(this);
        summonImageButton.setOnClickListener(this);
        collectionImageButton.setOnClickListener(this);
    }

    private void wireWidgets() {
        homeImageButton = findViewById(R.id.imageButtonHome);
        missionImageButton = findViewById(R.id.imageButtonMission);
        summonImageButton = findViewById(R.id.imageButtonSummon);
        collectionImageButton = findViewById(R.id.imageButtonCollection);
        coinText = findViewById(R.id.textView);

    }

    @Override
    public void onClick(View view) {

        Fragment currentFragment = null;

        switch (view.getId()) {
            case R.id.imageButtonHome:
                currentFragment = new FragmentHome();
                break;
            case R.id.imageButtonMission:
                currentFragment = new FragmentMission();
                break;
            case R.id.imageButtonSummon:
                currentFragment = new FragmentSummon();
                break;
            case R.id.imageButtonCollection:
                currentFragment = new FragmentCollection();
                break;


        }
        FragmentManager fm = getSupportFragmentManager();
        if(currentFragment != null)
        {
            fm.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
        }
    }



    @Override
    protected void onPause() {
        int[] temp = collection.getCollection();
        Set<String> buffer = collection.convertArrayToSet(temp);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(getString(R.string.collection_key),buffer);
        editor.putInt(getString(R.string.coin_key),collection.getCoin());
        editor.commit();

        Log.d(TAG, "onPause: Coin=" + collection.getCoin());

        super.onPause();


    }

    @Override
    protected void onResume() {


        if (ranConstructor)
        {
            ranConstructor = false;
        }
        if (!ranConstructor)
        {
            Set<String> temp = sharedPref.getStringSet(getString(R.string.collection_key),emptySet);
            int[] buffer = collection.convertSetToArray(temp);
            collection.setCollection(buffer);
            collection.setCoin(sharedPref.getInt(getString(R.string.coin_key),0));
        }

        super.onResume();



    }

    //override onPause to save to sharedpreferences whatever is in the collection

}
