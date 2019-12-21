package com.example.my119;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.my119.Login.applyinfos;
import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.ip;
import static com.example.my119.Login.noticeinfos;

public class RequestedNotice extends AppCompatActivity  {
    final static String TAG="final check";

    Button call;
    Button facecall;
    String tel;

    Button btn_check_resume;
    Button btn_check_star;
    Button btn_eva;
    Button btn_reject, btn_delete;
    Button btn_confirm;
    Button btn_show_contract;
    String ee_apply;
    String eid;
    String num;
    String ccompanyName;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_notice);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("대타신청확인");

        btn_delete = findViewById(R.id.btn_delete);
        btn_check_resume = (Button)findViewById(R.id.btn_check_resume);
        btn_check_star = (Button)findViewById(R.id.btn_check_star);
        btn_eva = (Button)findViewById(R.id.btn_eva);
        btn_reject = (Button)findViewById(R.id.btn_reject);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_show_contract = (Button)findViewById(R.id.btn_show_contract);
        linearLayout = (LinearLayout)findViewById(R.id.IfConfirmShow) ;

        Intent intent = getIntent();
        final String[] notices = intent.getStringArrayExtra("noticeInfos");

        //등록한 공고에 보여줄 정보들이다
        String str = notices[6];
        String[] array = str.split(" ");
        String k3_1= array[0];
        String k3_2 = array[1];

        TextView s_storeName = (TextView)findViewById(R.id.s_storeName);
        //여기서 intent.getExtras()를 이용하여 db에서 값 받아오기
        s_storeName.setText(notices[0].toString());

        //일급
        TextView s_money = (TextView)findViewById(R.id.s_money);
        s_money.setText(notices[1].toString());
        //근로날짜
        final TextView s_workDate = (TextView)findViewById(R.id.s_workDate);
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

        ee_apply = notices[10];
        eid = "";
        num = "";
        ccompanyName = "";

        call = findViewById(R.id.btn_call);
        facecall = findViewById(R.id.btn_face_call);

        for (int i = 0; i < employerinfos.size(); i++) {
            if (employerinfos.get(i).getID().equals(LoginEmployer.rID)) {
                ccompanyName = employerinfos.get(i).getCompayName();
            }
        }

        for (int i = 0; i < noticeinfos.size(); i++) {
            if (ccompanyName.equals(noticeinfos.get(i).getStoreName())) {
                num = noticeinfos.get(i).getNum();
            }
        }


        for (int i = 0; i < applyinfos.size(); i++) {
            if (applyinfos.get(i).getNum().equals(num)) {
                eid = applyinfos.get(i).getEid();
            }
        }
        for (int i = 0; i < employeeinfos.size(); i++) {
            if (employeeinfos.get(i).getID().equals(eid)) {
                tel = employeeinfos.get(i).getPhoneNum();
            }
        }

        //알바를 신청한 알바생의 이력서에서 핸드폰번호를 가져와 전화연결한다
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall();
            }
        });
        //알바를 신청한 알바생의 이력서에서 핸드폰번호를 가져와 영상통화를 연결한다
        facecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVideoCall();
            }
        });

        //true >> 만약 해당 공고의 상태가 '근무확정'일 경우에 로 바꾸기
        final String wD = s_workDate.getText().toString();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InsertData1 task = new InsertData1();
                task.execute("http://" + ip + "/delete_notice.php",  ccompanyName, wD);
                finish();
            }

        });

        //신청자 이력서 확인
        btn_check_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 확인", Toast.LENGTH_SHORT).show();
            }
        });

        //신청자 별점 확인
        btn_check_star.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i=0; i<employeeinfos.size();i++){
                    if(employeeinfos.get(i).getID().equals(applyinfos.get(Integer.valueOf(ee_apply)).getEid()))

                        Toast.makeText(getApplicationContext(), employeeinfos.get(i).getRate()+" 점", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //신청자 근무거절
        btn_reject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근무 거절", Toast.LENGTH_SHORT).show();
                InsertData task = new InsertData();
                task.execute("http://" +"10.0.2.2"+ "/final.php",eid,"3");
            }
        });

        //신청자 근무확정
        //만약 근무확정 상태라면 상호평가랑 계약서 버튼 보이게 만들기 / 근무 확정이 아니면 버튼 안보임.
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                btn_eva.setVisibility(View.VISIBLE);
                btn_show_contract.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "근무 확정", Toast.LENGTH_SHORT).show();
                InsertData task = new InsertData();
                task.execute("http://" +"10.50.96.112"+ "/final.php",eid,"2");

            }
        });

        //근무자(알바생) 평가하기
        btn_eva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근무자 상호평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employee.class);
                intent.putExtra("ee_apply",ee_apply);
                startActivity(intent);
                finish();
            }
        });

        //계약서 보기
        btn_show_contract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "계약서 보기", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Contract_employer.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //신청자와의 전화연결
    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+tel)));
        }
    }

    //신청자와의 영상통화연결
    public void onVideoCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+tel)).putExtra("videocall", true));
            }
    }

    //권한 허용을 하지 않았을 때 뜨는 화면이다
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }

    //근무에 대한 정보를 수정해준다 아직 근무확정을 하지 않았다면 1, 근무확정을 하면 2, 근무하길 원치 않아 근무를 거절하면 3으로 변경된다
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(RequestedNotice.this,
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

            String id = (String)params[1];
            String final_c= (String) params[2];
            String serverURL = (String) params[0];
            String postParameters = "eid="+id+"&fianl=" + final_c ;

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

    class InsertData1 extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(RequestedNotice.this,
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

            String storeName = (String)params[1];
            String date = (String) params[2];
            String serverURL = (String) params[0];
            String postParameters = "storeName="+ storeName +"&date=" + date ;

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
