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
    int er_apply;

    public String[] notice = new String[11];

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
                notice[0] =adapter.getStore(position);
                notice[1] =adapter.getMoney(position);
                notice[2] =adapter.getWorkDate(position);
                notice[3] =adapter.getEndTime(position);
                notice[4] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))).getKey1();
                notice[5] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))).getKey2();
                notice[6] =adapter.getWorkTime(position);
                notice[7] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))).getPaymethod();
                notice[8] = noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))).getInterview();
                notice[9]= adapter.getStore(position);
                notice[10]= String.valueOf(er_apply);

                Intent intent = new Intent(AppliedNoticeList.this, AppliedNotice.class);

                intent.putExtra("noticeInfos",notice);
                startActivity(intent);
            }
        });

    }


    private void setData(NoticeListViewAdapter adapter) {
        //리스트뷰에 데이터 추가
        int num = 1;
        for(int i =0; i<applyinfos.size(); i++){
            if(applyinfos.get(i).getEid().equals(LoginEmployee.eID)){
                er_apply=i;
                applyNum = Integer.valueOf(applyinfos.get(i).getNum())-1;
                adapter.addNotice(noticeinfos.get(applyNum).getDate(), noticeinfos.get(applyNum).getKey3(), noticeinfos.get(applyNum).getStoreName(),
                        noticeinfos.get(applyNum).getNum(), noticeinfos.get(applyNum).getEndtime(), noticeinfos.get(applyNum).getPay());
            }
        }


    }

}