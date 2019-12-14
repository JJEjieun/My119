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
import android.widget.Toast;

public class RequestedNotice extends AppCompatActivity  {

    Button call;
    Button facecall;
    String tel;

    Button btn_check_resume = (Button)findViewById(R.id.btn_check_resume);
    Button btn_check_star = (Button)findViewById(R.id.btn_check_star);
    Button btn_eva = (Button)findViewById(R.id.btn_eva);
    Button btn_reject = (Button)findViewById(R.id.btn_reject);
    Button btn_confirm = (Button)findViewById(R.id.btn_confirm);
    Button btn_show_contract = (Button)findViewById(R.id.btn_show_contract);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_notice);

        call = findViewById(R.id.btn_call);
        facecall = findViewById(R.id.btn_face_call);

//        Intent callIntent = new Intent()

        tel = "01023275437";

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall();
            }
        });

        facecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVideoCall();
            }
        });

        //true >> 만약 해당 공고의 상태가 '근무확정'일 경우에 로 바꾸기
        if(true){
            btn_eva.setVisibility(View.VISIBLE);
            btn_show_contract.setVisibility(View.VISIBLE);
        }

        //신청자 이력서 확인
        btn_check_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 확인", Toast.LENGTH_SHORT).show();
            }
        });

        //신청자 별점 확인
        btn_check_star.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 확인", Toast.LENGTH_SHORT).show();
            }
        });

        //신청자 근무거절
        btn_reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 확인", Toast.LENGTH_SHORT).show();
            }
        });

        //신청자 근무확정
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 확인", Toast.LENGTH_SHORT).show();
            }
        });

        //근무자(알바생) 평가하기
        btn_eva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근무자 평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employee.class);
                startActivity(intent);
                finish();
            }
        });

        //계약서 보기
        btn_show_contract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "계약서 보기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Contract_employer.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+tel)));
        }
    }

    public void onVideoCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+tel)).putExtra("videocall", true));
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }

}
