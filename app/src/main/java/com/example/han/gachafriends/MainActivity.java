package com.example.han.gachafriends;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
    }

    private void wireWidgets() {
        homeImageButton = (ImageButton) findViewById(R.id.imageButtonHome);
        missionImageButton = (ImageButton) findViewById(R.id.imageButtonMission);
        summonImageButton = (ImageButton) findViewById(R.id.imageButtonSummon);
        collectionImageButton = (ImageButton) findViewById(R.id.imageButtonCollection);

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
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        if(currentFragment != null)
        {
            fm.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
        }
    }
}
