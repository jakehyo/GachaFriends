package com.example.han.gachafriends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
//lol
public class MainActivity extends AppCompatActivity{

    private ImageButton homeImageButton,missionImageButton,summonImageButton,collectionImageButton;
    public static TextView coinText;
    private static int coin = 5;
    public static final String TAG = "TIM_DEBUG";
    public TextView mTextMessage;
    private Collection collection;
    private Set<String> emptySet = new HashSet<String>();
    private boolean ranConstructor;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


        wireWidgets();


        coinText.setText("Coins: " + coin);

        // Set up SharedPreference storage
        emptySet.add("0");
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        collection = new Collection();

        int[] bufferSet = collection.convertSetToArray(sharedPref.getStringSet(getString(R.string.collection_key), emptySet));
        int bufferCoin = sharedPref.getInt(getString(R.string.coin_key), 0);
        collection.setCoin(bufferCoin);
        collection.setCollection(bufferSet);


        ranConstructor = true;


        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
    }


    public static void addCoin(){
        coinText.setText("Coins: "+ ++coin);
    }
    public static int getCoin() { return coin;}
    public static void setCoin() {
        coin-=5;
        coinText.setText("Coins: "+ coin);
    }
    public static void resetCoin(){
        coin = 0;
        coinText.setText("Coins: "+ coin);
    }

    private void wireWidgets() {
        coinText = findViewById(R.id.textView);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment currentFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    currentFragment = new FragmentHome();
                    break;
                case R.id.navigation_collection:
                    currentFragment = new FragmentCollection();
                    break;
                case R.id.navigation_summon:
                    currentFragment = new FragmentSummon();
                    break;
                case R.id.navigation_mission:
                    currentFragment = new FragmentMission();
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            if(currentFragment != null){
                fm.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
            }
            return true;
        }
    };

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
