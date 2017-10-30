package com.example.han.gachafriends;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by per6 on 10/20/17.
 */

public class Collection {
    public static List<Friend> friends;

    public Collection()
    {
        friends = new ArrayList<>();
    }

    public void addFriend(Friend friend)
    {
        friends.add(friend);
    }
}
