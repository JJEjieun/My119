package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.Objects;

public class AssignEmployer extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
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

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;

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

        //스피너에 주소 입력.
        final Spinner spin1 = (Spinner)findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterAddress2);
        final Spinner spin3 = (Spinner)findViewById(R.id.enterAddress3);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner1, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin1.getItem(i), "서울")) {
                    adspin2 = ArrayAdapter.createFromResource(AssignEmployer.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin2.getItem(j),"성북구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_seongbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else{
                                spin3.setAdapter(null);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin2.setAdapter(null);
                    spin3.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


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

                    add1 = spin1.getSelectedItem().toString();
                    add2 = spin2.getSelectedItem().toString();
                    add3 = spin3.getSelectedItem().toString();

                    address = add1+" "+add2+" "+add3+" "+ mEditTextAddress.getText().toString();



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
//                mTextViewResult.setText(result);
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
