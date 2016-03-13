package com.example.administrator.mymapapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/13.
 */
public class BulidingGroup  {
    static List<String> BulidingGroup;
    BulidingGroup(){
        BulidingGroup = new ArrayList<>();
    }

    static  public void  addList(String in){
        BulidingGroup.add(in);
    }

    public static List<String> getBulidingGroup() {
        return BulidingGroup;
    }
}
