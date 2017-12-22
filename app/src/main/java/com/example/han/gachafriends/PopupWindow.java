package com.example.han.gachafriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;

import java.util.Set;


/**
 * Created by timot on 12/22/2017.
 */

public class PopupWindow extends DialogFragment{
    private Collection collection;
    private int friendID;
    private SharedPreferences preferences;
    public static PopupWindow newInstance(int id){
        PopupWindow temp = new PopupWindow();
        Bundle args = new Bundle();
        args.putInt("Friend Home ID Popup", id);
        temp.setArguments(args);
        return temp;
    }

    public interface PopupWindowListener{
        void onFinishPopup(int id);
    }

    public void sendBack(){
        PopupWindowListener listener = (PopupWindowListener) getTargetFragment();
        listener.onFinishPopup(friendID);
        dismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        friendID = getArguments().getInt("Friend Home ID Popup");
        Log.d("Tim debut temp", "onCreateDialog: " + friendID);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Set as home friend?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sendBack();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }



}
