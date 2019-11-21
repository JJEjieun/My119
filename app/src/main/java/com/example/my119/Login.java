package com.example.my119;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //'개인회원 로그인'버튼 클릭 시 개인회원 로그인 창으로 넘어감
        Button button_employee = (Button)findViewById(R.id.button_employee);
        button_employee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인회원 로그인", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginEmployee.class);
                startActivity(intent);
            }
        });

        //'기업회원 로그인'버튼 클릭 시 기업회원 로그인 창으로 넘어감
        Button button_employer = (Button)findViewById(R.id.button_employer);
        button_employer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업회원 로그인", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), LoginEmployer.class);
                startActivity(intent2);
            }
        });
    }

}
