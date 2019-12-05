package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        TextView name = (TextView)findViewById(R.id.mypageName);

//        name.setText(LoginEmployee.employeeinfos.get());
        name.setText(LoginEmployee.eName+"님");

        Button button_resume = (Button)findViewById(R.id.manageS);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 작성 및 변경", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Resume.class);
                startActivity(intent);
            }
        });


        Button button_supply = (Button)findViewById(R.id.supply);
        button_supply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근로 계약서", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Contract_employee.class);
                startActivity(intent2);
            }
        });

        Button button_ev = (Button)findViewById(R.id.ev);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employee.class);
                startActivity(intent);
                finish();
            }
        });

        Button button_ev_r = (Button)findViewById(R.id.ev_r);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employer.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
