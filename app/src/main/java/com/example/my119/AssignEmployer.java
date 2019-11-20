package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AssignEmployer extends AppCompatActivity {

    private static String IP_ADDRESS = "10.50.97.219";
    private static String TAG = "phptest";

    static String add1,add2,add3;

    private EditText mEditTextNum;//등록번호
    private EditText mEditTextID;//아이디
    private EditText mEditTextPW;//비밀번호
    private EditText mEditTextName;//사업주명
    private EditText mEditTextStore;//기업명
    private EditText mEditTextAddress;//주소
    private EditText mEditTextPhone;//전화번호
    private EditText mEditTextEmail;//이메일
    private Spinner address1;
    private Spinner address2;
    private Spinner address3;
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employer);
        //        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 사업자등록번호 맞는지 확인
//        5) 인증번호 맞아야 등록처리되게

        // 위의 항목들이 모두 정상적으로 처리 되었으면
        // '등록' 버튼 누르면 로그인창으로 돌아감

        mEditTextID = (EditText) findViewById(R.id.enterID);
        mEditTextPW = (EditText) findViewById(R.id.enterPW);
        mEditTextNum = (EditText) findViewById(R.id.enterEmployerNumber);
        mEditTextStore = (EditText) findViewById(R.id.enterCompanyName);
        mEditTextName = (EditText) findViewById(R.id.enterEmployerName);
        mEditTextAddress = (EditText) findViewById(R.id.enterAddress4);
        mEditTextPhone = (EditText) findViewById(R.id.enterPhoneNumber);
        mEditTextEmail = (EditText) findViewById(R.id.enterEmail);
        address1 = (Spinner)findViewById(R.id.enterAddress1);
        address2 = (Spinner)findViewById(R.id.enterAddress2);
        address3 = (Spinner)findViewById(R.id.enterAddress3);

        if (true) {
            Button assignButton = (Button) findViewById(R.id.assignButton);
            assignButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String id = mEditTextID.getText().toString();
                    String pw = mEditTextPW.getText().toString();
                    String employerNumber = mEditTextNum.getText().toString();
                    String companyName = mEditTextStore.getText().toString();
                    String name = mEditTextName.getText().toString();
                    String address="";
                    String phoneNum = mEditTextPhone.getText().toString();
                    String email = mEditTextEmail.getText().toString();

                    address1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            add1=parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    address2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            add2=parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    address3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            add3=parent.getItemAtPosition(position).toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    address = add1+add2+add3+ mEditTextAddress.getText().toString();



                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + "/assignEmployer.php",
                            id, pw, employerNumber, companyName, name, address, phoneNum, email);

                    if (mEditTextID.length() > 0) {
                        mEditTextID.getText().clear();
                    }
                    if (mEditTextPW.length() > 0) {
                        mEditTextPW.getText().clear();
                    }
                    if (mEditTextNum.length() > 0) {
                        mEditTextNum.getText().clear();
                    }
                    if (mEditTextStore.length() > 0) {
                        mEditTextStore.getText().clear();
                    }
                    if (mEditTextName.length() > 0) {
                        mEditTextName.getText().clear();
                    }
                    if (mEditTextAddress.length() > 0) {
                        mEditTextAddress.getText().clear();
                    }
                    if (mEditTextPhone.length() > 0) {
                        mEditTextPhone.getText().clear();
                    }
                    if (mEditTextEmail.length() > 0) {
                        mEditTextEmail.getText().clear();
                    }

                    Toast.makeText(getApplicationContext(), "가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(AssignEmployer.this,
                        "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                progressDialog.dismiss();
                mTextViewResult.setText(result);
                Log.d(TAG, "POST response1 - " + result);
            }

            @Override
            protected String doInBackground(String... params) {

                String id = (String) params[1];
                String pw = (String) params[2];
                String employerNumber = (String) params[3];
                String companyName = (String) params[4];
                String name = (String) params[5];
                String address = (String) params[6];
                String phoneNum = (String) params[7];
                String email = (String) params[8];

                String serverURL = (String) params[0];
                String postParameters = "id=" + id + "&pw=" + pw+ "&employerNumber=" +employerNumber+ "&companyName=" +companyName+ "&name=" +name
                        + "&address=" +address+ "&phoneNum=" +phoneNum+ "&email=" +email;


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
