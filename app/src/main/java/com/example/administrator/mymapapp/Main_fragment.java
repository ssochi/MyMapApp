package com.example.administrator.mymapapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2016/2/17.
 */
public class Main_fragment extends Fragment {
    MapView mapView;
    Button button;
    DrawerLayout mDrawerLayout;

    public Main_fragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main,container,false);
        button = (Button) root.findViewById(R.id.btntext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        return root;
    }
    public void getmDrawerLayout(DrawerLayout mDrawerLayout){
       this.mDrawerLayout = mDrawerLayout;
    }
}
