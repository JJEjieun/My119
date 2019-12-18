package com.example.my119;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.my119.Login.applyinfos;

public class AppliedNotice extends AppCompatActivity  {

    Button btn_eva;
    Button btn_show_contract;
    String er_apply;
    String applynum;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applied_notice);

        btn_eva = (Button)findViewById(R.id.btn_eva);
       btn_show_contract = (Button)findViewById(R.id.btn_show_contract);
       linearLayout = (LinearLayout)findViewById(R.id.applyLayout);

        Intent intent = getIntent();
        final String[] notices = intent.getStringArrayExtra("noticeInfos");

        String str = notices[6];
        String[] array = str.split(" ");
        String k3_1= array[0];
        String k3_2 = array[1];


        TextView s_storeName = (TextView)findViewById(R.id.s_storeName);
        //여기서 intent.getExtras()를 이용하여 db에서 값 받아오기
        s_storeName.setText(notices[0].toString());

        //일급
        TextView s_money = (TextView)findViewById(R.id.s_money);
        s_money.setText(notices[1].toString());
        //근로날짜
        TextView s_workDate = (TextView)findViewById(R.id.s_workDate);
        s_workDate.setText(notices[2].toString());

        //공고마감일
        TextView s_endTime = (TextView)findViewById(R.id.s_endTime);
        s_endTime.setText(notices[3].toString());
        //업종
        TextView s_key1 = (TextView)findViewById(R.id.s_key1);
        s_key1.setText(notices[4].toString());
        //동주소
        TextView s_key2 = (TextView)findViewById(R.id.s_key2);
        s_key2.setText(notices[5].toString());
        //시간
        TextView s_key3_start = (TextView)findViewById(R.id.s_key3_start);
        s_key3_start.setText(k3_1);
        TextView s_key3_end = (TextView)findViewById(R.id.s_key3_end);
        s_key3_end.setText(k3_2);
        //임금지급방법
        TextView s_giveMethod= (TextView)findViewById(R.id.s_giveMethod);
        s_giveMethod.setText(notices[7].toString());
        //면접방법
        TextView s_interview = (TextView)findViewById(R.id.s_interview);
        s_interview.setText(notices[8].toString());
        //가게평점
        TextView s_store_star= (TextView)findViewById(R.id.s_store_star);

        er_apply=notices[10];
        applynum = notices[11];


        //true >> 만약 해당 공고의 상태가 '근무확정'일 경우에 로 바꾸기
        if(applyinfos.get(applyinfos.size()-Integer.valueOf(applynum)-1).getFianl().equals("2")){
            linearLayout.setVisibility(View.VISIBLE);
            btn_eva.setVisibility(View.VISIBLE);
            btn_show_contract.setVisibility(View.VISIBLE);
        }

        //근무지(기업) 평가하기
        btn_eva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근무지 평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employer.class);
                intent.putExtra("er_apply",er_apply);
                startActivity(intent);
                finish();
            }
        });

        //계약서 보기
        btn_show_contract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "계약서 보기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Contract_employee.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
