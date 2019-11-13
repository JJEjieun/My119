package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AssignEmployee extends AppCompatActivity {
    boolean isVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employee);

        //인증번호 받기 버튼누르면 인증번호 확인 표시 뜨게
        Button checkPhoneNumber = (Button)findViewById(R.id.checkPhoneNumber);
        final TextView textAut = (TextView)findViewById(R.id.textAut);
        final EditText EnterAutNum = (EditText)findViewById(R.id.enterAutNumber);
        final Button butAutNum = (Button)findViewById(R.id.checkAutNumber);

        //인증번호가 맞으면. 나중에 if문 조건에 true부분을 수정해야함.
        if(true)
        checkPhoneNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!isVisible){
                    textAut.setVisibility(View.VISIBLE);
                    EnterAutNum.setVisibility(View.VISIBLE);
                    butAutNum.setVisibility(View.VISIBLE);
                }
            }
        });

        //인증번호 확인
        butAutNum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //인증번호 맞은 거 처리하기-if(true)부분 수정
                if(true) {
                    Toast.makeText(getApplicationContext(), "인증번호가 확인되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 생년월일 맞게 입력했는지
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