package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginEmployer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employer);

        // true부분을 (ID PW 검사해서 맞으면)으로 바꿔야함.
        // '로그인'버튼 누르면 기업회원 메인페이지로 넘어감.
        if(true){
            Button button_employer = (Button)findViewById(R.id.button_employer);
            button_employer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "기업회원 메인창", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainEmployer.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        //'기업회원가입'텍스트 클릭 시 기업회원가입 창으로 넘어감
        TextView goto_assign = (TextView)findViewById(R.id.goto_assign);
        goto_assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업회원가입", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssignEmployer.class);
                startActivity(intent);
            }
        });
    }
}