package com.example.my119;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.my119.Login.noticeinfos;
import static com.example.my119.Login.applyinfos;


public class AppliedNoticeList extends AppCompatActivity {
    int applyNum;

    public String[] notice = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applied_notice_list);


        final NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.lView1);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
                notice[0] =noticeinfos.get(applyNum).getStoreName();
                notice[1] =noticeinfos.get(applyNum).getPay();
                notice[2] =noticeinfos.get(applyNum).getDate();
                notice[3] =noticeinfos.get(applyNum).getEndtime();
                notice[4] =noticeinfos.get(applyNum).getKey1();
                notice[5] =noticeinfos.get(applyNum).getKey2();
                notice[6] =noticeinfos.get(applyNum).getKey3();
                notice[7] =noticeinfos.get(applyNum).getPaymethod();
                notice[8] = noticeinfos.get(applyNum).getInterview();
                notice[9]= String.valueOf(applyNum);

                Intent intent = new Intent(AppliedNoticeList.this, AppliedNotice.class);

                intent.putExtra("noticeInfo",notice);
                startActivity(intent);
            }
        });

    }


    private void setData(NoticeListViewAdapter adapter) {
        //리스트뷰에 데이터 추가
        int num = 1;
        for(int i =0; i<applyinfos.size(); i++){
//            int a = Integer.valueOf( noticeinfos.get(applyNum).getNum())+1;
            if(applyinfos.get(i).getEid().equals(LoginEmployee.eID)){
                applyNum = Integer.valueOf(applyinfos.get(i).getNum());
                adapter.addNotice(noticeinfos.get(applyNum).getDate(), noticeinfos.get(applyNum).getKey3(), noticeinfos.get(applyNum).getStoreName(),
                        noticeinfos.get(applyNum).getNum(), noticeinfos.get(applyNum).getEndtime(), noticeinfos.get(applyNum).getPay());
            }
        }


    }

}