package com.example.administrator.mymapapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    MapView Map;
    DrawerLayout mDrawerLayout;
    ListView lv_menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.Layout_Drawer);
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        if(savedInstanceState == null){

            Main_fragment main_fragment = new Main_fragment();
            main_fragment.getmDrawerLayout(mDrawerLayout);
            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,main_fragment).commit();
        }
        initListView();



    }

    private void initListView() {

        lv_menu = (ListView) findViewById(R.id.lv_menu);
        String[] s = new String[]{"学校地图","社区互动","我的信息","软件介绍"};
        lv_menu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));

    }
}
