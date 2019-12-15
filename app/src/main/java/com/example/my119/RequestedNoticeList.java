package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.my119.Login.noticeinfos;


public class RequestedNoticeList extends AppCompatActivity {

    ArrayList<String> notices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applied_notice_list);

        final NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
                notices.add(noticeinfos.get(position).getStoreName());
                notices.add(noticeinfos.get(position).getPay());
                notices.add(noticeinfos.get(position).getDate());
                notices.add(noticeinfos.get(position).getEndtime());
                notices.add(noticeinfos.get(position).getKey1());
                notices.add(noticeinfos.get(position).getKey2());
                notices.add(noticeinfos.get(position).getKey3());
                notices.add(noticeinfos.get(position).getPaymethod());
                notices.add(noticeinfos.get(position).getInterview());

                Intent intent = new Intent(RequestedNoticeList.this, RequestedNotice.class);

                intent.putStringArrayListExtra("noticeInfo",notices);
                startActivity(intent);
            }
        });
    }

    private void setData(NoticeListViewAdapter adapter) {
        //리스트뷰에 데이터 추가
    }

}