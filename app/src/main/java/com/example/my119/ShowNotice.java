package com.example.my119;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


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
//        String noticeNum = intent.getStringExtra("noticeNum");
        ArrayList<String> notices = intent.getStringArrayListExtra("noticeInfo");

        String str = notices.get(6);
        String[] array = str.split(" ");
        String k3_1= array[0];
        String k3_2 = array[1];



        //가게이름
        TextView s_storeName = (TextView)findViewById(R.id.s_storeName);
        //여기서 intent.getExtras()를 이용하여 db에서 값 받아오기
        s_storeName.setText(notices.get(0).toString());

        //일급
        TextView s_money = (TextView)findViewById(R.id.s_money);
        s_money.setText(notices.get(1).toString());
        //근로날짜
        TextView s_workDate = (TextView)findViewById(R.id.s_workDate);
        s_workDate.setText(notices.get(2).toString());

        //공고마감일
        TextView s_endTime = (TextView)findViewById(R.id.s_endTime);
        s_endTime.setText(notices.get(3).toString());
        //업종
        TextView s_key1 = (TextView)findViewById(R.id.s_key1);
        s_key1.setText(notices.get(4).toString());
        //동주소
        TextView s_key2 = (TextView)findViewById(R.id.s_key2);
        s_key2.setText(notices.get(5).toString());
        //시간
        TextView s_key3_start = (TextView)findViewById(R.id.s_key3_start);
        s_key3_start.setText(k3_1);
        TextView s_key3_end = (TextView)findViewById(R.id.s_key3_end);
        s_key3_end.setText(k3_2);
        //임금지급방법
        TextView s_giveMethod= (TextView)findViewById(R.id.s_giveMethod);
        s_giveMethod.setText(notices.get(7).toString());
        //면접방법
        TextView s_interview = (TextView)findViewById(R.id.s_interview);
        s_interview.setText(notices.get(8).toString());
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