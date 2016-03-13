package com.example.administrator.mymapapp;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

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
public class Baseadpter_IntroduceBuilding extends BaseAdapter {
    Context context;
    List<String> List_title ;
    List<List<String>> Line2List;
    List<String> line2;
    FragmentManager fragmentManager;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    String URl ="http://1.mapapi2233.applinzi.com/api/single/" ;
    boolean init_ready = false;


    public Baseadpter_IntroduceBuilding(Context context,FragmentManager fragmentManager,List<List<String>> list,List<String>  line2){
        this.context = context;
        mInflater = LayoutInflater.from(context);
        List_title = new ArrayList<>();
        List_title.add("历史");
        List_title.add("职能");
        List_title.add("活动");
        List_title.add("福利");
        List_title.add("树洞");
        this.fragmentManager = fragmentManager;
        Line2List = list;
        this.line2 = line2;
        init_ready = true;


    }
    public Baseadpter_IntroduceBuilding(Context context,FragmentManager fragmentManager){
        this.context = context;
        mInflater = LayoutInflater.from(context);
        List_title = new ArrayList<>();
        List_title.add("历史");
        List_title.add("职能");
        List_title.add("活动");
        List_title.add("福利");
        List_title.add("树洞");
        this.fragmentManager = fragmentManager;
        init_ready = false;

    }

    @Override
    public int getCount() {
        return List_title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_item_card,null);
            viewHolder = new ViewHolder();
            viewHolder.line1 = (TextView) convertView.findViewById(R.id.line1);
            viewHolder.line2 = (TextView) convertView.findViewById(R.id.line2);
            viewHolder.btnAdd = (Button) convertView.findViewById(R.id.btnAdd);
            viewHolder.btnMore = (Button) convertView.findViewById(R.id.btnMore);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
        viewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choose_static.setChoose(List_title.get(position));
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container, new item_fragment()).commit();


            }
        });
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choose_static.setChoose(List_title.get(position));
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.container,new AnswerFragment()).commit();
            }
        });

        viewHolder.line1.setText(List_title.get(position) + ":");
        if(init_ready){
//            if(Line2List.get(position).size()>0){
//                viewHolder.line2.setText(Line2List.get(position).get(0));
//            }else {
//                viewHolder.line2.setText("NULL");
//            }
            viewHolder.line2.setText(line2.get(position));
        }





        return convertView;
    }

//    public void getLine2() {
//        line2 = new ArrayList<>();
//        HttpGet();
//
//
//    }
//    private void HttpGet() {
//
//        for(int i = 0;i<Line2List.size();i++){
//            if(Line2List.get(i).size() > 0){
//                getContent(URl,Line2List.get(i).get(0));
//            }else {
//                line2.add("NULL");
//            }
//        }
//    }
//
//    public void getContent(String url,String name) {
//
//        String URL = "";
//        try {
//            URL = url + URLEncoder.encode(name, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        final String finalURL = URL;
//
//        String out = NetUtil.getRequestGetData(finalURL);
//        while (true){
//            if(out == "Fail"){
//                out = NetUtil.getRequestGetData(finalURL);
//            }else {
//                break;
//            }
//        }
//        try {
//            JSONObject root = new JSONObject(out);
//            Iterator<?> it = root.keys();
//            String aa2 = "";
//            String bb2 = null;
//            while(it.hasNext()){//遍历JSONObject
//                bb2 = (String) it.next().toString();
//                aa2 =  root.getString(bb2);
//            }
//            line2.add(aa2);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//    }

    class ViewHolder{
        TextView line1;
        TextView line2;
        Button btnAdd;
        Button btnMore;
        public ViewHolder(){}
    }
}

