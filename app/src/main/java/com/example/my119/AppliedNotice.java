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
import android.widget.Toast;

public class AppliedNotice extends AppCompatActivity  {

    Button btn_eva;
    Button btn_show_contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_notice);

        btn_eva = (Button)findViewById(R.id.btn_eva);
       btn_show_contract = (Button)findViewById(R.id.btn_show_contract);

        //true >> 만약 해당 공고의 상태가 '근무확정'일 경우에 로 바꾸기
        if(true){
            btn_eva.setVisibility(View.VISIBLE);
            btn_show_contract.setVisibility(View.VISIBLE);
        }

        //근무지(기업) 평가하기
        btn_eva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근무지 평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employer.class);
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
