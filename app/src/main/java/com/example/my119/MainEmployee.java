package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_employee);

        //마이페이지 버튼 누르면 마이페이지 창으로 넘어감
        Button btnMyPage = (Button)findViewById(R.id.MyPage);
        btnMyPage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "마이페이지", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });

        // 최신 알림 표시할 Adapter 생성
        AlarmListViewAdapter adapter1 = new AlarmListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        ListView listview1 = (ListView) findViewById(R.id.listView1);
        listview1.setAdapter(adapter1);
        // 아이템 추가.(항목 두 개만 넣기)
/**/        adapter1.addAlarm("면접봅시다", "대타구해요");
/**/        adapter1.addAlarm("합격", "대타급함");

        // '알림 더 확인하기' 버튼 누르면 알림 확인 창으로 넘어감
        Button btnMoreAlarm = (Button)findViewById(R.id.button1);
        btnMoreAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "알림 더 확인", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CheckAlarm.class);
                startActivity(intent);
        }
        });


        // 최신 공고 표시할 Adapter 생성
        NoticeListViewAdapter adapter2 = new NoticeListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        ListView listview2 = (ListView) findViewById(R.id.listView2);
        listview2.setAdapter(adapter2);
        // 아이템 추가.(항목 두 개만 넣기)
/**/        adapter2.addNotice("알바구함", "191212","할리스커피", "8500");
/**/        adapter2.addNotice("대타구해요", "200130","이디야", "8700");

        // '공고 더 확인하기' 버튼 누르면 공고 확인 창으로 넘어감
        Button btnMoreNotice = (Button)findViewById(R.id.button2);
        btnMoreNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "공고 더 확인", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CheckNotice.class);
                startActivity(intent);
            }
        });
    }
}