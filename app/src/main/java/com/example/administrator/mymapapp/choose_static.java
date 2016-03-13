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

    public static String getIndex(){

        switch (choose){
            case  "历史" :
                return "History";
            case  "职能" :
                return "Job";
            case  "活动" :
                return "Activity";
            case  "福利" :
                return "Bouns";
            case  "树洞" :
                return "treeHoles";

        }
        return "err";
    }
}
