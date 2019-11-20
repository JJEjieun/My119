package com.example.my119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


public class CheckNotice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

        // Adapter 생성
        NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);

        // 아이템 추가.
/**/        //이부분을 php에서 값 받아오는 걸로 수정
        adapter.addNotice("알바구함", "191212","할리스커피", "8500");
        adapter.addNotice("대타구해요", "200130","이디야", "8700");
        adapter.addNotice("대타급함", "191124","애정마라", "8400");

    }

}
