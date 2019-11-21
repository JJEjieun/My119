package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class CheckNotice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

            //새 공고 등록 버튼 누르면 공고 등록 창으로 넘어감
            Button btnNewNotice = (Button)findViewById(R.id.newNotice);
            btnNewNotice.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "새 공고 등록", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RegisterNotice.class);
                    startActivity(intent);
                }
            });

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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

}
