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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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

import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.noticeinfos;

public class MyPage extends AppCompatActivity {

    TextView tv;

    String fid = "";
    String fname = "";

    Button button_resume;
    ImageButton button_modify_employee;

    String id, name, storeName;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 상단바의 어플 이름을 지정해주는 부분을 변경
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("마이페이지");
        setContentView(R.layout.mypage);

        tv=(TextView)findViewById(R.id.rateText);
//        tv.setText(String.valueOf(Float.valueOf(LoginEmployee.erate)));

        RatingBar rb = (RatingBar)findViewById(R.id.ratingbar);
//        rb.setRating(Math.round(Float.valueOf(LoginEmployee.erate)));

        TextView name = (TextView)findViewById(R.id.mypageName);
//        name.setText(LoginEmployee.employeeinfos.get());
        name.setText(LoginEmployee.eName+"님");


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
                Intent intent2 = new Intent(getApplicationContext(), Contract_main.class);
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


        // Adapter 생성
        final FriendAdapter adapter = new FriendAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.lv_friend_mypage);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

    }

    private void setData(FriendAdapter adapter) {
        for (int i = 0; i < Login.friendInfos.size(); i++) {
            if (Login.friendInfos.get(i).getPerson().equals(LoginEmployee.eID)) {
                fid = LoginEmployee.eID;
                fname = LoginEmployee.eName;
                for (int j = 0; j < Login.employerinfos.size(); j++) {
                    if (Login.employerinfos.get(j).getID().equals(Login.friendInfos.get(j).getBoss())) {
                        storeName = employerinfos.get(j).getCompayName();
                        adapter.addFriends(fid, fname, storeName);
                    }
                }
            }
        }
    }

    // 메뉴 생성하기 위해 필요한 부분
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
