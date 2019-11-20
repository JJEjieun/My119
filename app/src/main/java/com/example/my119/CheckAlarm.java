package com.example.my119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class CheckAlarm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_alarm);

        // Adapter 생성
        AlarmListViewAdapter adapter = new AlarmListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);

        // 아이템 추가.
/**/       //이부분을 php에서 값 받아오는 걸로 수정
        adapter.addAlarm("면접봐요~","알바구함");
        adapter.addAlarm("합격","대타구해요");
        adapter.addAlarm("불합격입니다","대타급함");

    }

}