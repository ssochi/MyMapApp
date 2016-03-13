package com.example.administrator.mymapapp;

/**
 * Created by Administrator on 2016/2/13.
 */
public class choose_static {
    static String choose ;
    static String position;

    public static void setChoose(String choose) {
        choose_static.choose = choose;
    }

    public static void setPosition(String position) {
        choose_static.position = position;
    }

    public static String getPosition() {
        return position;
    }

    public static String getChoose() {
        return choose;
    }
}
