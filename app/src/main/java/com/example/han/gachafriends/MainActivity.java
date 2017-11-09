package com.example.han.gachafriends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;
    private TextView coinText;
    private int coin = 5;
    public static final String TAG = "TAGG";
    private Collection collection;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setOnClickListeners();

        coinText.setText("Coins: "+ coin);

        // Make the hashset to array conversion a method

        collection = new Collection();
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);


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
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //override onPause to save to sharedpreferences whatever is in the collection

}
