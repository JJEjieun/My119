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
import android.widget.TextView;
import android.widget.Toast;

import static com.example.my119.Login.applyinfos;
import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.noticeinfos;

public class RequestedNotice extends AppCompatActivity  {

    Button call;
    Button facecall;
    String tel;

    Button btn_check_resume;
    Button btn_check_star;
    Button btn_eva;
    Button btn_reject;
    Button btn_confirm;
    Button btn_show_contract;
    String eid;
    String num;
    String ccompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_notice);

        btn_check_resume = (Button)findViewById(R.id.btn_check_resume);
        btn_check_star = (Button)findViewById(R.id.btn_check_star);
        btn_eva = (Button)findViewById(R.id.btn_eva);
        btn_reject = (Button)findViewById(R.id.btn_reject);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_show_contract = (Button)findViewById(R.id.btn_show_contract);

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

        eid = "";
        num = "";
        ccompanyName = "";

        call = findViewById(R.id.btn_call);
        facecall = findViewById(R.id.btn_face_call);

//        Intent callIntent = new Intent()


        for (int i = 0; i < employerinfos.size(); i++) {
            if (employerinfos.get(i).getID().equals(LoginEmployer.rID)) {
                ccompanyName = employerinfos.get(i).getCompayName();
            }
        }

        for (int i = 0; i < noticeinfos.size(); i++) {
            if (ccompanyName.equals(noticeinfos.get(i).getStoreName())) {
                num = noticeinfos.get(i).getNum();
            }
        }


        for (int i = 0; i < applyinfos.size(); i++) {
            if (applyinfos.get(i).getNum().equals(num)) {
                eid = applyinfos.get(i).getEid();
            }
        }
        for (int i = 0; i < employeeinfos.size(); i++) {
            if (employeeinfos.get(i).getID().equals(eid)) {
                tel = employeeinfos.get(i).getPhoneNum();
            }
        }

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
                Toast.makeText(getApplicationContext(), "근무 거절", Toast.LENGTH_SHORT).show();
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
