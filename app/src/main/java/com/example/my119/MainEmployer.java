package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainEmployer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_employer);

        // 최신 알림 표시할 Adapter 생성
/**/        NoticeListViewAdapter adapter1 = new NoticeListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        ListView listview1 = (ListView) findViewById(R.id.listView1);
        listview1.setAdapter(adapter1);
/**/        // 아이템 추가.(항목 두 개만 넣기)
/**/        adapter1.addNotice("알바구함", "191212","할리스커피", "8500");
/**/        adapter1.addNotice("대타구해요", "200130","이디야", "8700");

        // '알림 더 확인하기' 버튼 누르면 알림 확인 창으로 넘어감
        Button btnMoreAlarm = (Button)findViewById(R.id.button1);
        btnMoreAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "알림 더 확인", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CheckNotice.class);
                startActivity(intent);
            }
        });

    }
}