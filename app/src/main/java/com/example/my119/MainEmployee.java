package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static com.example.my119.Login.noticeinfos;

public class MainEmployee extends AppCompatActivity {
    static  int numApply;
    int num;

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

        // 최신 공고 표시할 Adapter 생성
        final NoticeListViewAdapter adapter2 = new NoticeListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        ListView listview2 = (ListView) findViewById(R.id.listView2);
        setData(adapter2);
        listview2.setAdapter(adapter2);
        // 아이템 추가 아래는 예시
//    adapter2.addNotice("191212","13~15시","할리스커피",
//                "3", "191210","28500");
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

    private void setData(NoticeListViewAdapter adapter2) {

        for(int i = noticeinfos.size()-1; i >= 0;i--){
            int count = 0;
            num = i+1;
            noticeinfos.get(i).setNum(String.valueOf(num));
            numApply=Integer.valueOf(noticeinfos.get(i).getNum());
            adapter2.addNotice(noticeinfos.get(i).getDate(), noticeinfos.get(i).getKey3(), noticeinfos.get(i).getStoreName(),
                    noticeinfos.get(i).getNum(), noticeinfos.get(i).getEndtime(), noticeinfos.get(i).getPay());
            count++;
        }
    }
}