package com.example.administrator.mymapapp;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/13.
 */
public class Baseadpter_item extends BaseAdapter {
    Context context;
    boolean init_ready = false;
    String name;
    FragmentManager fragmentManager;
    List<String> line2;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    int num_user ;
    public Baseadpter_item(Context context,FragmentManager fragmentManager,String name){
        this.context = context;
        mInflater = LayoutInflater.from(context);
        num_user = 1 + (int)  (Math.random()*10);
        this.fragmentManager = fragmentManager;
        init_ready = false;
        this.name = name;

    }
    public Baseadpter_item(Context context,FragmentManager fragmentManager,String name,List<String> line2){
        this.context = context;
        mInflater = LayoutInflater.from(context);
        num_user = 1 + (int)  (Math.random()*10);
        this.name = name;
        this.line2 = line2;
        init_ready = true;

    }

    @Override
    public int getCount() {

        if(init_ready){
            return line2.size();
        }else {
            return 0;
        }
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
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHolder.line1.setText("用户" + position);
        if(init_ready){
            viewHolder.line2.setText(line2.get(position));
        }
        viewHolder.btnAdd.setText("赞");
        viewHolder.btnMore.setText("踢");
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragmentManager.beginTransaction().addToBackStack(null).replace().commit();
            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView line1;
        TextView line2;
        Button btnAdd;
        Button btnMore;
        public ViewHolder(){}
    }
}