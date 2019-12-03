package com.example.my119;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShowNotice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notice);

        //'닫기'버튼 클릭 시 공고창 닫기
        Button btn_end_screen = (Button)findViewById(R.id.btn_end_screen);
        btn_end_screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //'신청'버튼 클릭 시 점주 측 푸시알람으로 신청알람 받게
        Button btn_apply = (Button)findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //아래 사항들 디비에서 가져오기
        Intent intent = getIntent(); //얘가 공고번호목록 arrayList(CheckNotice의 noticeNumList)를 읽어옴
        //noticeNum이 공고를 구분할 수 있는 주키로 쓰면 될 듯!
        String noticeNum = intent.getStringExtra("noticeNum");


        //가게이름
        TextView s_storeName = (TextView)findViewById(R.id.s_storeName);
        //여기서 intent.getExtras()를 이용하여 db에서 값 받아오기

        //일급
        TextView s_money = (TextView)findViewById(R.id.s_money);
        //근로날짜
        TextView s_workDate = (TextView)findViewById(R.id.s_workDate);
        //공고마감일
        TextView s_endTime = (TextView)findViewById(R.id.s_endTime);
        //업종
        TextView s_key1 = (TextView)findViewById(R.id.s_key1);
        //동주소
        TextView s_key2 = (TextView)findViewById(R.id.s_key2);
        //시간
        TextView s_key3_start = (TextView)findViewById(R.id.s_key3_start);
        TextView s_key3_end = (TextView)findViewById(R.id.s_key3_end);
        //임금지급방법
        TextView s_giveMethod= (TextView)findViewById(R.id.s_giveMethod);
        //면접방법
        TextView s_interview = (TextView)findViewById(R.id.s_interview);
        //가게평점
        TextView s_store_star= (TextView)findViewById(R.id.s_store_star);
        //올린회원이름
        TextView s_user_name = (TextView)findViewById(R.id.s_user_name);
        //회원분류
        TextView s_user_state = (TextView)findViewById(R.id.s_user_state);
        //회원평점
        TextView s_user_star = (TextView)findViewById(R.id.s_user_star);

    }
}