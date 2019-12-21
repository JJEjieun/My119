package com.example.my119;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import static com.example.my119.Login.noticeinfos;


public class ShowNotice extends AppCompatActivity {

    private static final String TAG = "apply";
    private static String IP_ADDRESS = "10.0.2.2";
    public String eid;
    public String num;
    public String storeName;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notice);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("공고등록");

        //아래 사항들 디비에서 가져오기
        Intent intent = getIntent(); //얘가 공고번호목록 arrayList(CheckNotice의 noticeNumList)를 읽어옴
        //noticeNum이 공고를 구분할 수 있는 주키로 쓰면 될 듯!
//        String noticeNum = intent.getStringExtra("noticeNum");
        final String[] notices = intent.getStringArrayExtra("noticeInfo");


        //'닫기'버튼 클릭 시 공고창 닫기
        Button btn_end_screen = (Button)findViewById(R.id.btn_end_screen);
        btn_end_screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //'신청'버튼 클릭 시 점주 측 푸시알람으로 신청알람 받게
        Button btn_apply = (Button)findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//        eid = PreferenceUtil.getPreferences(context, "pref");
                eid = LoginEmployee.eID;
                num = String.valueOf(Integer.valueOf(notices[9])+1);


                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/apply.php",
                        num, eid);

                Toast.makeText(getApplicationContext(), "신청되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });




        String str = notices[6];
        String[] array = str.split(" ");
        String k3_1= array[0];
        String k3_2 = array[1];



        //가게이름
        TextView s_storeName = (TextView)findViewById(R.id.s_storeName);
        //여기서 intent.getExtras()를 이용하여 db에서 값 받아오기
        s_storeName.setText(notices[0].toString());

        //일급
        TextView s_money = (TextView)findViewById(R.id.s_money);
        s_money.setText(notices[1].toString());
        //근로날짜
        TextView s_workDate = (TextView)findViewById(R.id.s_workDate);
        s_workDate.setText(notices[2].toString());

        //공고마감일
        TextView s_endTime = (TextView)findViewById(R.id.s_endTime);
        s_endTime.setText(notices[3].toString());
        //업종
        TextView s_key1 = (TextView)findViewById(R.id.s_key1);
        s_key1.setText(notices[4].toString());
        //동주소
        TextView s_key2 = (TextView)findViewById(R.id.s_key2);
        s_key2.setText(notices[5].toString());
        //시간
        TextView s_key3_start = (TextView)findViewById(R.id.s_key3_start);
        s_key3_start.setText(k3_1);
        TextView s_key3_end = (TextView)findViewById(R.id.s_key3_end);
        s_key3_end.setText(k3_2);
        //임금지급방법
        TextView s_giveMethod= (TextView)findViewById(R.id.s_giveMethod);
        s_giveMethod.setText(notices[7].toString());
        //면접방법
        TextView s_interview = (TextView)findViewById(R.id.s_interview);
        s_interview.setText(notices[8].toString());
        //가게평점
        TextView s_store_star= (TextView)findViewById(R.id.s_store_star);

    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(ShowNotice.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response1 - " + result);
        }

        @Override
        protected String doInBackground(String... params) {
            String num = (String) params[1];
            String eid = (String) params[2];

            String serverURL = (String) params[0];
            String postParameters = "num=" + num + "&eid=" + eid;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code2 - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.d(TAG, "OK");
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }

    }
}