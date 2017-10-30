package com.example.han.gachafriends;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;
    public static final String TAG = "TAGG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setOnClickListeners();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, new FragmentSummon()).commit();
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


}
