package com.example.my119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowResume extends AppCompatActivity {
    ImageView userFace = (ImageView)findViewById(R.id.myFace);  //신청인 이력서 사진
    TextView userName = (TextView)findViewById(R.id.yourname);  //신청인 이름
    TextView userGender = (TextView)findViewById(R.id.gender);  //신청인 성별
    TextView userStartTime = (TextView)findViewById(R.id.db_enterKey3of1);  //신청인 희망근무 시작시간
    TextView userEndTime = (TextView)findViewById(R.id.db_enterKey3of2);  //신청인 희망근무 종료시간
    TextView userAddress1 = (TextView)findViewById(R.id.db_enterAddress1);  //신청인 희망근무지1 시/도
    TextView userAddress2 = (TextView)findViewById(R.id.db_enterAddress2);  //신청인 희망근무지1 시/군/구
    TextView userAddress3 = (TextView)findViewById(R.id.db_enterAddress3);  //신청인 희망근무지2 시/도
    TextView userAddress4 = (TextView)findViewById(R.id.db_enterAddress4);  //신청인 희망근무지2 시/군/구
    TextView userCategory = (TextView)findViewById(R.id.db_enterCagegory);  //신청인 희망업직종
    TextView userWords = (TextView)findViewById(R.id.words);  //신청인 하고싶은말

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_resume);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("이력서보기");

        //userName.setText("홍길동"); 이런식으로 입력하면될 듯,,!

    }
}
