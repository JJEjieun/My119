package com.example.my119;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyPage extends AppCompatActivity {
    Button button_resume;
    ImageButton button_modify_employee;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("마이페이지");
        setContentView(R.layout.mypage);

        TextView name = (TextView)findViewById(R.id.mypageName);

//        name.setText(LoginEmployee.employeeinfos.get());
        name.setText(LoginEmployee.eName+"님");

        button_modify_employee = (ImageButton)findViewById(R.id.button_modifyee);
        button_modify_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "회원정보변경", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyInfo.class);
                startActivity(intent);
            }
        });

        button_resume = (Button)findViewById(R.id.resume);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 작성 및 변경", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Resume.class);
                startActivity(intent);
            }
        });


        Button button_supply = (Button)findViewById(R.id.contract);
        button_supply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근로 계약서", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Contract_employee.class);
                startActivity(intent2);
            }
        });

        Button button_ev = (Button)findViewById(R.id.applyednotice);
        button_ev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인평가", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), Evaluation_employee.class);
                startActivity(intent3);
                finish();
            }
        });

        // xml 변경 하기 위해서 바꿨으니 이 부분 한 사람 꼭 확인하기 필요없는 주석 아님!
//        Button button_ev_r = (Button)findViewById(R.id.ev_r);
//        button_ev_r.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "기업평가", Toast.LENGTH_SHORT).show();
//                Intent intent4 = new Intent(getApplicationContext(), Evaluation_employer.class);
//                startActivity(intent4);
//                finish();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mainPage:
                setContentView(R.layout.main_employee);
                return true;
            case R.id.resume:
                setContentView(R.layout.resume);
                return true;
            case R.id.contract:
                setContentView(R.layout.contract_employee);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
