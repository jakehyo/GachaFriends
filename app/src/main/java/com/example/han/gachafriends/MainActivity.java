package com.example.han.gachafriends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;
    public TextView coinText;
    public int coin = 5;
    public static final String TAG = "TAGG";
    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        /*BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment currentFragment = null;
                        switch (item.getItemId()) {
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
                            return currentFragment;
                        }
*/      wireWidgets();

        coinText.setText("Coins: "+ coin);
    }

    private void wireWidgets() {
        coinText = (TextView) findViewById(R.id.coin_text);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment currentFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    currentFragment = new FragmentHome();
                    break;
                case R.id.navigation_dashboard:
                    currentFragment = new FragmentCollection();
                    break;
                case R.id.navigation_notifications:
                    currentFragment = new FragmentSummon();
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            if(currentFragment != null){
                fm.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
            }
            return true;
        }
    };
}
