package com.example.han.gachafriends;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by per6 on 10/20/17.
 */

public class Collection implements Parcelable {

    private int[] collection;
    private int coin;
    private int homeFriend;

    public Collection()
    {
        this.collection = new int[]{1};
    }

    public int[] convertSetToArray(Set<String> temp)
    {
        String[] temp2 = temp.toArray(new String[temp.size()]);
        int[] ids = new int[temp2.length];
        for (int i = 0; i < ids.length;i++)
        {
            ids[i] = Integer.parseInt(temp2[i]);
        }
        return ids;
    }

    public void addFriend(int id)
    {
        int[] temp = new int[collection.length+1];
        for (int i =0; i < collection.length; i++)
        {
            temp[i] = collection[i];
        }
        temp[collection.length] = id;
        collection = temp;
    }

    public ArrayList<Friend> getFriendList(Context context){
        ArrayList<Friend> friendArrayList = new ArrayList<>();
        for (int i = 0; i < collection.length; i++)
        {
            friendArrayList.add(new Friend(collection[i], context));
        }
        return friendArrayList;
    }

    public Friend getFriend(int id, Context context){
        return new Friend(collection[id], context);
    }

    public Set<String> convertArrayToSet(int[] temp) {
        String[] temp2 = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            temp2[i] = temp[i] + "";
        }
        Set<String> ids = new HashSet<String>(Arrays.asList(temp2));
        return ids;
    }

    public void addCoin(int i)
    {
        coin +=i;
    }

    public void removeCoin(int i)
    {
        coin -= i;
    }

    public void updateCoin()
    {
        MainActivity.coinText.setText("Coins: "+coin);
    }


    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void resetCoin()
    {
        this.coin = 0;
    }

    public void setHomeFriend(int friendID){
        homeFriend = friendID;
    }

    public int getHomeFriend(){
        return homeFriend;
    }

    public int[] getCollection() {
        return collection;
    }

    public void setCollection(int[] collection) {
        this.collection = collection;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.collection);
        dest.writeInt(this.coin);
    }

    protected Collection(Parcel in) {
        this.collection = in.createIntArray();
        this.coin = in.readInt();
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collection=" + Arrays.toString(collection) +
                '}';
    }

    public static final Parcelable.Creator<Collection> CREATOR = new Parcelable.Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel source) {
            return new Collection(source);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };
}
