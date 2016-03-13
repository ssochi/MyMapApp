package com.example.administrator.mymapapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.*;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/22.
 */
public class AnswerFragment extends Fragment {
    Button btnSubmit;
    EditText editText;
    String url = "http://1.mapapi2233.applinzi.com/api/single";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answer,container,false);
        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        editText = (EditText) rootView.findViewById(R.id.et);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    HttpPOST(url,choose_static.getPosition(),choose_static.getChoose(),editText.getText().toString());
            }
        });


        return rootView;
    }

    private void HttpPOST(String url,  final String building,String sort,String content) {
        new AsyncTask<String, Void, Void>() {

            @Override
            protected Void doInBackground(String... arg0) {
                String url = arg0[0];
                String buliding = arg0[1];
                String sort = arg0[2];
                String content = arg0[3];


                Map<String, String> params = new HashMap<String, String>();
                String post_result = null;
                System.out.println(building);
                System.out.println(sort);
                System.out.println(content);
                params.put("building", buliding);
                params.put("sort", sort);
                params.put("content", content);

                int timer = 10;
                while (timer>0){
                    try {
                        post_result = HttpUtils.submitPostData(params, "utf-8", url);
                        Log.i("POST_RESULT", post_result);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    if(post_result !="Fail")
                        break;
                }
                if(post_result == "Fail"){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                getFragmentManager().popBackStack();


                return null;

            }
        }.execute(url, building, sort, content);
    }

}
