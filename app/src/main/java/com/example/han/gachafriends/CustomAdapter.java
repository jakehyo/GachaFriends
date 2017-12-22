package com.example.han.gachafriends;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.han.gachafriends.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by timot on 12/21/2017.
 */

public class CustomAdapter extends ArrayAdapter<Friend>{
    private Context mContext;
    private ArrayList<Friend> friendList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Friend> objects) {
        super(context, resource, objects);
        mContext = context;
        friendList = (ArrayList) objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.grid_image, null);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.frame_image);
            holder.text = convertView.findViewById(R.id.friend_name_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setImageResource(friendList.get(position).getFrameId());
        holder.text.setText(friendList.get(position).getName());
        holder.text.setGravity(1);
        return convertView;
    }

    static class ViewHolder{
        TextView text;
        ImageView image;
    }
}
