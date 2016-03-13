package com.example.administrator.mymapapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2016/2/13.
 */
public class buildingIntroduceActivity extends SwipeBackActivity {
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        initListView();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new introduce_fragement()).commit();
        }


//        textView = (TextView) findViewById(R.id.buildingIntroduceTextVeiw);
//        textView.setText("这是   "+ getInputText(getIntent().getStringExtra("name") ));

    }
    private void initListView() {
        ListView lv_menu;
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        String[] s = new String[]{"学校地图","社区互动","我的信息","软件介绍"};
        lv_menu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));

    }


}
