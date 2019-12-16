package com.example.my119;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyPage extends AppCompatActivity {


    ArrayList<String> notices = new ArrayList<>();
    TextView tv;


    Button button_resume;
    ImageButton button_modify_employee;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("마이페이지");
        setContentView(R.layout.mypage);

        tv=(TextView)findViewById(R.id.rateText);
        tv.setText(String.valueOf(Float.valueOf(LoginEmployee.erate)));

        RatingBar rb = (RatingBar)findViewById(R.id.ratingbar);
        rb.setRating(Math.round(Float.valueOf(LoginEmployee.erate)));



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

        Button button_appliedNotice = (Button)findViewById(R.id.appliednotice);
        button_appliedNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청한 공고 확인", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), AppliedNoticeList.class);
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
