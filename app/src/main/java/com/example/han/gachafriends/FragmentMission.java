package com.example.han.gachafriends;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMission.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMission#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMission extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "TAGG";
    public  TextView actionText;
    public int action;
    public int player;
    private ArrayList<String> actions;
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button m1Button, m2Button, m3Button, upButton, downButton, rightButton, leftButton;
    private OnFragmentInteractionListener mListener;


    public FragmentMission() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMission.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMission newInstance(String param1, String param2) {
        FragmentMission fragment = new FragmentMission();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void setOnClickListeners() {
        m1Button.setOnClickListener(this);
        //m2Button.setOnClickListener(this);
        //m3Button.setOnClickListener(this);
        upButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);

            m1Button = view.findViewById(R.id.buttonM1);
            //m2Button = view.findViewById(R.id.buttonM2);
            //m3Button = view.findViewById(R.id.buttonM3);
            upButton = view.findViewById(R.id.buttonUp);
            downButton = view.findViewById(R.id.buttonDown);
            leftButton = view.findViewById(R.id.buttonLeft);
            rightButton = view.findViewById(R.id.buttonRight);
            actionText = view.findViewById(R.id.textViewAction);
            setOnClickListeners();
            createActions();
        }



    /**@SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button[] buttonArray = new Button[3];
        LinearLayout layout = (LinearLayout) getView().findViewById(R.id.MissionLayout);
        int[] buttonIds = new int[3];
        for(int i = 0; i <= 2; i++) {
            Button temp = new Button(getContext());
            int id = View.generateViewId();
            temp.setId(id);
            temp.setText("Mission" + (i + 1));
            buttonArray[i] = temp;
            buttonIds[i] = id;
            layout.addView(temp);
        }



    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_mission, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
               /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
 private void createActions() {
        actions = new ArrayList<String>();
        actions.add("Cerelean strikes to the right.");
        actions.add("Cerelean strikes to the left.");
        actions.add("Cerelean strikes above.");
        actions.add("Cerelean strikes below.");
        actions.add("Cerelean blocks right.");
        actions.add("Cerelean blocks left.");
        actions.add("Cerelean blocks up.");
        actions.add("Cerelean blocks down.");
 }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
             case R.id.buttonM1:
                 actionText.setVisibility(View.VISIBLE);
                 actionText.setText(actions.get((int) (Math.random()*7)));
                 action = actions.indexOf(actionText.getText().toString());
                 m1Button.setEnabled(false);
                 if(action == 0 || action == 4) {
                     action = 1;
                 }
                else if(action == 1 || action == 5) {
                     action = 2;
                 }
                 else if(action == 2 || action == 6) {
                     action = 3;
                 }
                 else if(action == 3 || action == 7) {
                     action = 4;
                 }
            break;
             //case R.id.buttonM2:
            //actionText.setVisibility(View.VISIBLE);
            //actionText.setText(actions.get((int) (Math.random()*7)));
            // MainActivity.addCoin();
             //MainActivity.addCoin();
             //MainActivity.addCoin();
            //break;
            // case R.id.buttonM3:
            //actionText.setVisibility(View.VISIBLE);
                 //actionText.setText(actions.get((int) (Math.random()*7)));
             //MainActivity.addCoin();
             //MainActivity.addCoin();
            // MainActivity.addCoin();
             //MainActivity.addCoin();
            // MainActivity.addCoin();
             //break;
            case R.id.buttonRight:
               player = 2;
                if(action == player) {
                    MainActivity.addCoin();
                    action = 0;
                    m1Button.setEnabled(true);
                    actionText.setText("Mission Success");
                }
                    else{actionText.setText("Mission Fail");
                    m1Button.setEnabled(true);
                    }

               break;
            case R.id.buttonLeft:
            player = 1;
                if(action == player) {
                    MainActivity.addCoin();
                    action = 0;
                    m1Button.setEnabled(true);
                    actionText.setText("Mission Success");
                }
                else{actionText.setText("Mission Fail");
                    m1Button.setEnabled(true);
                }
            break;
            case R.id.buttonUp:
                player = 4;
                if(action == player) {
                    MainActivity.addCoin();
                    action = 0;
                    m1Button.setEnabled(true);
                    actionText.setText("Mission Success");
                }
                else{actionText.setText("Mission Fail");
                    m1Button.setEnabled(true);
                }
                break;
            case R.id.buttonDown:
                player = 3;
                if(action == player) {
                    MainActivity.addCoin();
                    action = 0;
                    m1Button.setEnabled(true);
                    actionText.setText("Mission Success");
                }
                else{actionText.setText("Mission Fail");
                    m1Button.setEnabled(true);
                }
                break;
             }
        }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    /**public int getCoin() {
        return coin;
    }
    public void setCoin(int coin) {
        this.coin = coin;
    }**/
}

