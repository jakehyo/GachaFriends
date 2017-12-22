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

import java.util.Arrays;
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
    private Summon summon;
    private Bundle bundle;

    private Set<String> emptySet = new HashSet<String>();
    private boolean ranConstructor;
    private SharedPreferences sharedPref;

    public Fragment fragmentHome;
    public Fragment fragmentCollection;
    public Fragment fragmentSummon;
    public Fragment fragmentMission;
    public Fragment previousFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


        wireWidgets();

        // Set up SharedPreference storage
        emptySet.add("7");
        Log.d(TAG, "onCreate: " + Arrays.asList(emptySet));
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        collection = new Collection();
        summon = new Summon(this);






        int[] bufferSet = collection.convertSetToArray(sharedPref.getStringSet(getString(R.string.collection_key), emptySet));
        //In case zero appears in collection

        int bufferCoin = sharedPref.getInt(getString(R.string.coin_key), 0);
        Log.d(TAG, "onCreate Set: " + bufferSet[0]);
        collection.setCoin(bufferCoin);
        collection.setCollection(bufferSet);


        ranConstructor = true;

        coinText.setText("Coins: " + collection.getCoin());

        fragmentCollection = new FragmentCollection();
        fragmentHome = new FragmentHome();
        fragmentMission = new FragmentMission();
        fragmentSummon = new FragmentSummon();

        bundle = new Bundle();
        bundle.putParcelable(getString(R.string.collection),collection);
        bundle.putParcelable(getString(R.string.summon),summon);
        fragmentHome.setArguments(bundle);
        previousFragment = fragmentHome;

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, fragmentHome).commit();
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
                    bundle = previousFragment.getArguments();
                    collection = bundle.getParcelable(getString(R.string.collection));
                    summon = bundle.getParcelable(getString(R.string.summon));

                    bundle.putParcelable(getString(R.string.collection), collection);
                    bundle.putParcelable(getString(R.string.summon), summon);

                    fragmentHome.setArguments(bundle);
                    currentFragment = fragmentHome;
                    previousFragment = fragmentHome;
                    break;
                case R.id.navigation_collection:
                    bundle = previousFragment.getArguments();
                    collection = bundle.getParcelable(getString(R.string.collection));
                    summon = bundle.getParcelable(getString(R.string.summon));

                    bundle.putParcelable(getString(R.string.collection), collection);
                    bundle.putParcelable(getString(R.string.summon), summon);

                    fragmentCollection.setArguments(bundle);
                    currentFragment = fragmentCollection;
                    previousFragment = fragmentCollection;
                    break;
                case R.id.navigation_summon:
                    bundle = previousFragment.getArguments();
                    collection = bundle.getParcelable(getString(R.string.collection));
                    summon = bundle.getParcelable(getString(R.string.summon));

                    bundle.putParcelable(getString(R.string.collection), collection);
                    bundle.putParcelable(getString(R.string.summon), summon);

                    fragmentSummon.setArguments(bundle);
                    currentFragment = fragmentSummon;
                    previousFragment = fragmentSummon;

                    break;
                case R.id.navigation_mission:
                    bundle = previousFragment.getArguments();
                    collection = bundle.getParcelable(getString(R.string.collection));
                    summon = bundle.getParcelable(getString(R.string.summon));

                    bundle.putParcelable(getString(R.string.collection), collection);
                    bundle.putParcelable(getString(R.string.summon), summon);

                    fragmentMission.setArguments(bundle);
                    currentFragment = fragmentMission;
                    previousFragment = fragmentMission;
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            if(currentFragment != null){
                fm.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
            }
            return true;
        }
    };

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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