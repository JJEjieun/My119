package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CheckNotice extends AppCompatActivity {
    ArrayList<Integer> noticeNumList = new ArrayList<Integer>();
    ArrayList<Notice> noticeList = new ArrayList<Notice>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

            //새 공고 등록 버튼 누르면 공고 등록 창으로 넘어감. 공고 등록하고 메인으로 돌아감
            Button btnNewNotice = (Button)findViewById(R.id.newNotice);
            btnNewNotice.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "새 공고 등록", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RegisterNotice.class);
                    startActivity(intent);
                    finish();
                }
            });

        // Adapter 생성
        NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        //디비에서 정보가져와서 리스트 추가 한 후 al.add(공고목록번호); 꼭 추가해주기
        //아래 listsview.setOnItemClickListener에서 공고목록번호로 데이터 정보를 일부 넘길 예정이기때문
        //혹은 그냥 noticeList 내용을 다 가져올까? intetn.putExtra("workD", noticeList.get(position).workD)이런식으로?

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setData(NoticeListViewAdapter adapter) {
        adapter.addNotice("191212","13~15시","할리스커피",
                "3", "191210","28500");
        adapter.addNotice("200130","15-20시", "이디야",
                "2", "200120","58700");
        adapter.addNotice( "191124","12-19","애정마라",
                "1", "191120","68400");
    }

}
