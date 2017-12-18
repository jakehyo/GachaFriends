package com.example.han.gachafriends;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSummon.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSummon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSummon extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private Button summonButton;
    private ImageView image;
    private TextView name;
    private OnFragmentInteractionListener mListener;
    private final String TAG = "TAGG";
    private Collection collection;
    private Summon summon;

    private final int FRIEND_IMAGE_HEIGHT = 140;

    public FragmentSummon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSummon.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSummon newInstance(String param1, String param2) {
        FragmentSummon fragment = new FragmentSummon();
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
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        summonButton = view.findViewById(R.id.SummonButton);
        summonButton.setOnClickListener(this);
        image = view.findViewById(R.id.imageViewTemp);
        name = view.findViewById(R.id.textViewName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_summon, container, false);
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
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.collection),collection);
        bundle.putParcelable(getString(R.string.summon),summon);
        setArguments(bundle);
    }

    @Override
    public void onClick(View view) {
        if (MainActivity.getCoin() >= 5) {
            Friend tempFriend = summon.summon();

            Drawable tempImageDrawable = getResources().getDrawable(tempFriend.getImageId());
            Bitmap bitmap = ((BitmapDrawable) tempImageDrawable).getBitmap();

            /*int drawableHeight = tempImageDrawable.getIntrinsicHeight();
            int drawableWidth = tempImageDrawable.getIntrinsicWidth();

            int scaledWidth = FRIEND_IMAGE_HEIGHT * drawableWidth / drawableHeight;
            int scaledHeight = FRIEND_IMAGE_HEIGHT;

            Drawable imageScaled = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight , true));
            image.setMaxHeight(scaledHeight);
            image.setMaxWidth(scaledWidth);
*/
            image.setImageResource(tempFriend.getImageId());
            name.setText(summon.getName());
            MainActivity.setCoin();
            Log.d(TAG, "onClick: ");
        }
       else if(MainActivity.getCoin() <= 5) {

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
}
