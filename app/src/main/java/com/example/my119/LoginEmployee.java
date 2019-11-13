package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginEmployee extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employee);

        // true부분을 (ID PW 검사해서 맞으면)으로 바꿔야함.
        // '로그인'버튼 누르면 개인회원 메인페이지로 넘어감.
        if(true){
            Button button_employee = (Button)findViewById(R.id.button_employee);
            button_employee.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "개인회원 메인창", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainEmployee.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        //'개인회원가입'텍스트 클릭 시 개인회원가입 창으로 넘어감
        TextView goto_assign = (TextView)findViewById(R.id.goto_assign);
        goto_assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인회원가입", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssignEmployee.class);
                startActivity(intent);
            }
        });
    }
}