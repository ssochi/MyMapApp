package com.example.administrator.mymapapp;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/13.
 */
public class IFFIRSTRUN {
    private static boolean IFFIRSTRUN;
    public void setIFFIRSTRUN(boolean IFFIRSTRUN) {
        this.IFFIRSTRUN = IFFIRSTRUN;
    }

    public  static boolean IFFIRSTRUN()
    {
       return  IFFIRSTRUN;
    }

}
