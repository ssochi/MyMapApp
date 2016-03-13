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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/2/13.
 */
public class item_fragment extends Fragment {

    Baseadpter_item MyAdpter;
    private ListView listView;
    ListView title;
    List<String> ContentIdList;
    List<String>  ContentList;
    String URl ="http://1.mapapi2233.applinzi.com/api/single/" ;
    String ContentIDURl ="http://1.mapapi2233.applinzi.com/api/list/" ;
    choose_static chooseStatic;
    String index;
    public item_fragment(){
        ContentList = new ArrayList<>();
        ContentIdList = new ArrayList<>();
    }
    public item_fragment(String index){
        ContentList = new ArrayList<>();
        ContentIdList = new ArrayList<>();
        this.index = index;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item,container,false);
        chooseStatic = new choose_static();
        title = (ListView) rootView.findViewById(R.id.Title_lv);
        String[] s = new String[]{chooseStatic.getChoose()};
        title.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.card_title, R.id.line1, s));


        if(!IFFIRSTRUN.IFFIRSTRUN()){
            List<String> list = new ArrayList<>();
            SQLitedb db = new SQLitedb(getActivity());
            SQLiteDatabase dbRead = db.getReadableDatabase();
            Cursor c = dbRead.query(index+choose_static.getIndex(),null,null,null,null,null,null);
            while(c.moveToNext()){
                String cout = c.getString(c.getColumnIndex("Content"));
                list.add(cout);
            }
            MyAdpter = new Baseadpter_item(getActivity(),getFragmentManager(),chooseStatic.getChoose(),list,index);
        }else {
            MyAdpter = new Baseadpter_item(getActivity(),getFragmentManager(),chooseStatic.getChoose(),index);
        }



        listView = (ListView) rootView.findViewById(R.id.card_listView);

        /*添加头和尾*/
        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));
//        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item_card, R.id.line1, data));

        listView.setAdapter(MyAdpter);
        HttpGet();
        return rootView;

    }

    private void HttpGet() {



        new Thread(new Runnable() {

            @Override
            public void run() {
                //访问网络要在子线程中实现，使用get取数据
                HttpGetContentId(ContentIDURl,choose_static.getPosition());
               for(int i =0 ;i<ContentIdList.size();i++){
                   String URL = "";
                   try {
                       URL = URl + URLEncoder.encode(ContentIdList.get(i), "UTF-8");
                   } catch (UnsupportedEncodingException e) {
                       e.printStackTrace();
                   }
                   String out = NetUtil.getRequestGetData(URL);
                   while (true){
                       if(out == "Fail"){
                           out = NetUtil.getRequestGetData(URL);
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
                       ContentList.add(aa2);

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

               }

                SQLitedb db = new SQLitedb(getActivity());
                ContentValues cv = new ContentValues();
                SQLiteDatabase dbWrite = db.getWritableDatabase();
                dbWrite.execSQL("DELETE FROM " + index+choose_static.getIndex());
                for (int i = 0;i<ContentList.size();i++){
                    cv = new ContentValues();
                    cv.put("Content", ContentList.get(i));
                    dbWrite.insert(index+choose_static.getIndex(), null, cv);
                }
                dbWrite.close();

                MyAdpter = new Baseadpter_item(getActivity(),getFragmentManager(),chooseStatic.getChoose(),ContentList,index);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(MyAdpter);
                    }
                });


                //执行在主线程上

            }
        }).start();
    }

    private void HttpGetContentId(String url,String buildingName) {
        ContentIdList = new ArrayList<>();
        String URL = "";
        try {
            URL = url + URLEncoder.encode(buildingName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalURL = URL;

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
                        if(aa2.equals(choose_static.getChoose())){
                            ContentIdList.add(bb2);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //执行在主线程上

            }



}
