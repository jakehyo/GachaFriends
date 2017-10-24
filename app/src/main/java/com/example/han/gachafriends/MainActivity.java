package com.example.han.gachafriends;

import android.os.Bundle;
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
    }

    private void wireWidgets() {
        homeImageButton = (ImageButton) findViewById(R.id.imageButtonHome);
        missionImageButton = (ImageButton) findViewById(R.id.imageButtonMission);
        summonImageButton = (ImageButton) findViewById(R.id.imageButtonSummon);
        collectionImageButton = (ImageButton) findViewById(R.id.imageButtonCollection);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonHome:

                break;

            case R.id.imageButtonMission:
                break;

            case R.id.imageButtonSummon:
                break;

            case R.id.imageButtonCollection:
                break;


        }
    }
}
