package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AssignEmployer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employer);
        //        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 사업자등록번호 맞는지 확인
//        5) 인증번호 맞아야 등록처리되게

        // 위의 항목들이 모두 정상적으로 처리 되었으면
        // '등록' 버튼 누르면 로그인창으로 돌아감
        if(true) {
            Button assignButton = (Button) findViewById(R.id.assignButton);
            assignButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

}