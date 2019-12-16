package com.example.my119;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.example.my119.Login.employerinfos;

public class LoginEmployer extends AppCompatActivity {

//    String url ="http://10.50.96.112/login_employer.php";
//    public GettingPHP gphp;

    private EditText enterPw;
    private EditText enterId;
//    final ArrayList<Employerinfo> employerinfos = new ArrayList<>();
    static String rID, rPW,rEmployerNumber,rCompanyName, rName, rAddress, rPhoneNum, rEmail,r_rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employer);

        // true부분을 (ID PW 검사해서 맞으면)으로 바꿔야함.
        // '로그인'버튼 누르면 기업회원 메인페이지로 넘어감.

        enterId = (EditText)findViewById(R.id.id);
        enterPw = (EditText)findViewById(R.id.pw);

//        gphp = new GettingPHP();
//        gphp.execute(url);

            Button button_employer = (Button)findViewById(R.id.button_employer);
            button_employer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String eId = enterId.getText().toString();
                    for(int i=0; i<employerinfos.size();i++){
                        if(employerinfos.get(i).getID().equals(eId)){
                            rID = employerinfos.get(i).getID();
                            rPW= employerinfos.get(i).getPW();
                            rEmployerNumber = employerinfos.get(i).getEmployerNumber();
                            rCompanyName = employerinfos.get(i).getCompayName();
                            rName = employerinfos.get(i).getName();
                            rAddress = employerinfos.get(i).getAddress();
                            rPhoneNum = employerinfos.get(i).getPhoneNum();
                            rEmail = employerinfos.get(i).getEmail();
                            r_rate= employerinfos.get(i).getRate();
                            Toast.makeText(getApplicationContext(), "기업회원 메인창", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainEmployer.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    if(!rID.equals(eId))
                        Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            });


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