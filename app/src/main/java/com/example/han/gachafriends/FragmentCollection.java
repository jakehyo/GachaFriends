package com.example.han.gachafriends;

import android.app.ActionBar;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCollection.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCollection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCollection extends Fragment implements View.OnClickListener, com.example.han.gachafriends.PopupWindow.PopupWindowListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GridView gridView;
    private ArrayAdapter<Friend> friendArrayAdapter;
    private Collection collection;
    private ArrayList<Friend> friendArrayList;
    private Summon summon;
    private ImageButton close;
    private ImageView friendImage;
    private TextView friendName;
    private PopupWindow window;
    private android.support.v4.app.FragmentManager manager;


    public FragmentCollection() {

    }

    public static FragmentCollection newInstance(String param1, String param2) {
        FragmentCollection fragment = new FragmentCollection();
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

            Bundle bundle = getArguments();
            if(bundle != null) {
                collection =  bundle.getParcelable(getString(R.string.collection));
                summon =  bundle.getParcelable(getString(R.string.summon));
                summon.setContext(getActivity());
                summon.setupText();
            }
        friendArrayList = collection.getFriendList(getContext());
    }

    private void setupGridView() {
       //friendArrayAdapter = new ArrayAdapter<Friend>(getContext(), android.R.layout.simple_list_item_1, collection.getFriendList(getContext()));

       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Friend tappedFriend = collection.getFriend(i, getContext());
               DialogFragment newFrag = (DialogFragment) com.example.han.gachafriends.PopupWindow.newInstance(tappedFriend.getId());
               newFrag.setTargetFragment(FragmentCollection.this, 300);
               newFrag.show(getFragmentManager(), "dialog");

                Log.d(MainActivity.TAG, "onItemClick: " + tappedFriend.getName());

           }
       });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        gridView = getView().findViewById(R.id.collection_view);
        setupGridView();

        gridView.setAdapter(new CustomAdapter(getContext(), R.layout.grid_image, friendArrayList));
    }

    private void setupPopup() {
        close = new ImageButton(getContext());
        close.setOnClickListener(this);
        friendImage = new ImageView(getContext());
        friendName = new TextView((getContext()));
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.close_x){
            window.dismiss();
        }
    }

    @Override
    public void onFinishPopup(int id) {
        collection.setHomeFriend(id);
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


}
