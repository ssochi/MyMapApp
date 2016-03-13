package com.example.administrator.mymapapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/2/13.
 */
public class introduce_fragement extends Fragment {

    Baseadpter_IntroduceBuilding MyAdpter;
    private ListView listView;
    ListView title;
    List<List<String>> MainList;
    List<String> History;
    List<String> Job;
    List<String> Activity;
    List<String> Bouns;
    List<String> treeHole;
    List<String> line2;
    String URl ="http://1.mapapi2233.applinzi.com/api/list/" ;
    String URlContent = "http://1.mapapi2233.applinzi.com/api/single/";
    String URlPOST = "http://1.mapapi2233.applinzi.com/api/single";
    String name;
    String index;
    boolean init_ready = false;
    Thread httpGetThread;
    Thread HttpGetContentThread;
    public introduce_fragement(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_introduce,container,false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        initListView(rootView);
        initList();
    }

    private void initList() {
        MainList = new ArrayList<>();
        History = new ArrayList<>();
        Job = new ArrayList<>();
        Activity = new ArrayList<>();
        Bouns = new ArrayList<>();
        treeHole = new ArrayList<>();
        line2 = new ArrayList<>();
        getMainList();



    }

    private void initListView(View rootView ) {
        title = (ListView) rootView.findViewById(R.id.Title_lv);
        String[] s = new String[]{getInputText(getActivity().getIntent().getStringExtra("name"))};
        name = s[0];
        index = getInputText(getActivity().getIntent().getStringExtra("index"));
        choose_static.setPosition(name);
        title.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.card_title, R.id.line2, s));

        if(!IFFIRSTRUN.IFFIRSTRUN()){
            List<String> list = new ArrayList<>();
            SQLitedb db = new SQLitedb(getActivity());
            SQLiteDatabase dbRead = db.getReadableDatabase();
            Cursor c = dbRead.query(index,null,null,null,null,null,null);
            while(c.moveToNext()){
                String cout = c.getString(c.getColumnIndex("Content"));
                list.add(cout);
            }
            if (list.size()<2){

                MyAdpter = new Baseadpter_IntroduceBuilding(getActivity(),getFragmentManager(),index);
            }else {
                MyAdpter = new Baseadpter_IntroduceBuilding(getActivity(),getFragmentManager(),null,list,index);
            }

        }else {
            MyAdpter = new Baseadpter_IntroduceBuilding(getActivity(),getFragmentManager(),index);
        }



        listView = (ListView) rootView.findViewById(R.id.card_listView);
        /*添加头和尾*/
        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));
//        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item_card, R.id.line1, data));
        listView.setAdapter(MyAdpter);
    }

    public String getInputText(String in) {
        Scanner sc = new Scanner(in);
        String out = "" ;
        while (sc.hasNext()){
            out += sc.nextLine();
        }
        return out;
    }
    public void getMainList() {
        HttpGet(URl, name);




    }
    private void HttpGet(String url,String buildingName) {
         String URL = "";
        try {
            URL = url + URLEncoder.encode(buildingName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalURL = URL;
         httpGetThread = new Thread(new Runnable() {

            @Override
            public void run() {
                //访问网络要在子线程中实现，使用get取数据
                String out = NetUtil.getRequestGetData(finalURL);
                while (true){
                    if(out == "Fail"){
                         out = NetUtil.getRequestGetData(finalURL);
                    }else {
                        break;
                    }
                }



                try {
                    JSONObject root = new JSONObject(out);

                    Iterator<?> it = root.keys();
                    String aa2 = "";
                    String bb2 = null;
                    while(it.hasNext()){//遍历JSONObject
                        bb2 = (String) it.next().toString();
                        aa2 =  root.getString(bb2);
                        switch (aa2){
                            case "历史" :
                                History.add(bb2);
                                break;
                            case "职能" :
                                Job.add(bb2);
                                break;
                            case "活动" :
                                Activity.add(bb2);
                                break;
                            case "福利" :
                                Bouns.add(bb2);
                                break;
                            case "树洞":
                                treeHole.add(bb2);
                                break;

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MainList.add(History);
                MainList.add(Job);
                MainList.add(Activity);
                MainList.add(Bouns);
                MainList.add(treeHole);
                HttpGetContent();





                //执行在主线程上

            }
        });
        httpGetThread.start();
    }


    private void HttpGetContent() {

        HttpGetContentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<MainList.size();i++) {

                    if (MainList.get(i).size() > 0) {
                        getContent(URlContent, MainList.get(i).get(MainList.get(i).size()-1));
                    } else {
                        line2.add("NULL");
                    }
                }
                MyAdpter = new Baseadpter_IntroduceBuilding(getActivity(),getFragmentManager(),MainList,line2,index);



                SQLitedb db = new SQLitedb(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase dbWrite = db.getWritableDatabase();
                dbWrite.execSQL("DELETE FROM " + index);
                for (int i = 0;i<line2.size();i++){
                    cv = new ContentValues();
                    cv.put("Content", line2.get(i));
                    dbWrite.insert(index, null, cv);
                }
                dbWrite.close();






                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(MyAdpter);
                    }
                });

            }
        });
        HttpGetContentThread.start();
    }

    public void getContent(String url,String name) {

        String URL = "";
        try {
            URL = url + URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalURL = URL;

        String out = NetUtil.getRequestGetData(finalURL);
        while (true){
            if(out == "Fail"){
                out = NetUtil.getRequestGetData(finalURL);
            }else {
                break;
            }
        }
        try {
            JSONObject root = new JSONObject(out);
            Iterator<?> it = root.keys();
            String aa2 = "";
            String bb2 = null;
            while(it.hasNext()){//遍历JSONObject
                bb2 = (String) it.next().toString();
                aa2 =  root.getString(bb2);
            }
            line2.add(aa2);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
