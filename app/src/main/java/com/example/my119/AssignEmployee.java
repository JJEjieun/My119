package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AssignEmployee extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    private FirebaseAuth fbAuth;
    private EditText enterAutNumber;
    private Button checkPhoneNumber;
    private Button checkAutNumber;
    private String phoneNumber;
    private String codeSent;
    private EditText enterPhoneNumber;
    private String phoneVerificationId;

    static String add1,add2,add3;
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private static String IP_ADDRESS = "10.0.2.2";
    //private static String TAG = "phptest";

    private EditText mEditTextBirth;//생년월일
    private EditText mEditTextID;//아이디
    private EditText mEditTextPW;//비밀번호
    private EditText mEditTextAddress;//주소
    private EditText mEditTextPhone;//전화번호
    private EditText mEditTextName;//이름
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private RadioGroup radioGroup;
    private Spinner address1;
    private Spinner address2;
    private Spinner address3;
    private Button check;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employee);

        fbAuth = FirebaseAuth.getInstance();

//        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 생년월일 맞게 입력했는지
//        5) 인증번호 맞아야 등록처리되게


        mEditTextID = (EditText) findViewById(R.id.enterID);
        mEditTextPW = (EditText) findViewById(R.id.enterPW);
        mEditTextName = (EditText) findViewById(R.id.enterName);
        mEditTextBirth = (EditText)findViewById(R.id.enterBornDate);
        //mEditTextAddress = (EditText) findViewById(R.id.enterAddress4);
        mEditTextPhone = (EditText) findViewById(R.id.enterPhoneNumber);
        femaleButton = (RadioButton)findViewById(R.id.enterGender1);
        maleButton = (RadioButton)findViewById(R.id.enterGender2);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        address1 = (Spinner)findViewById(R.id.enterAddress1);
        address2 = (Spinner)findViewById(R.id.enterAddress2);
        address3 = (Spinner)findViewById(R.id.enterAddress3);
        check = (Button)findViewById(R.id.checkID);

        enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
//        phoneNumber = enterPhoneNumber.getText().toString();
        enterAutNumber = findViewById(R.id.enterAutNumber);
        checkPhoneNumber = findViewById(R.id.checkPhoneNumber);
        checkAutNumber = findViewById(R.id.checkAutNumber);

        final TextView textAut = (TextView)findViewById(R.id.textAut);

        checkPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAut.setVisibility(View.VISIBLE);
                enterAutNumber.setVisibility(View.VISIBLE);
                checkAutNumber.setVisibility(View.VISIBLE);
                sendVerificationCode();
            }
        });

        checkAutNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
            }
        });

        //스피너에 주소 입력.
        final Spinner spin1 = (Spinner)findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterAddress2);
        final Spinner spin3 = (Spinner)findViewById(R.id.enterAddress3);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin1.getItem(i), "서울")) {
                    adspin2 = ArrayAdapter.createFromResource(AssignEmployee.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin2.getItem(j),"성북구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
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
        if(true) {
            Button assignButton = (Button) findViewById(R.id.assignButton);
            assignButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String id = mEditTextID.getText().toString();
                    String pw = mEditTextPW.getText().toString();
                    String name = mEditTextName.getText().toString();
                    String addr = "";
                    String phoneNum = mEditTextPhone.getText().toString();
                    String birth = mEditTextBirth.getText().toString();
                    String gender="";
//                    switch (radioGroup.getCheckedRadioButtonId()){
//                        case R.id.enterGender1:
//                            gender = "여자";
//                        case R.id.enterGender2:
//                            gender = "남자";
//                    }

                    if(femaleButton.isChecked()){
                        gender="여자";
                    }else if(maleButton.isChecked()){
                        gender="남자";
                    }


                    add1 = spin1.getSelectedItem().toString();
                    add2 = spin2.getSelectedItem().toString();
                    add3 = spin3.getSelectedItem().toString();

                    addr = add1+" "+add2+" "+add3;

                    AssignEmployee.InsertData task = new AssignEmployee.InsertData();
                    task.execute("http://" + IP_ADDRESS + "/assignEmployee.php",
                            id, pw, name, birth, gender,phoneNum,addr);

                    if (mEditTextID.length() > 0) {
                        mEditTextID.getText().clear();
                    }
                    if (mEditTextPW.length() > 0) {
                        mEditTextPW.getText().clear();
                    }
                    if (mEditTextName.length() > 0) {
                        mEditTextName.getText().clear();
                    }
                    if (mEditTextPhone.length() > 0) {
                        mEditTextPhone.getText().clear();
                    }
                    if (mEditTextBirth.length() > 0) {
                        mEditTextBirth.getText().clear();
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

            progressDialog = ProgressDialog.show(AssignEmployee.this,
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

            String id = (String) params[1];
            String pw = (String) params[2];
            String name = (String) params[3];
            String birth = (String) params[4];
            String gender = (String) params[5];
            String phoneNum = (String) params[6];
            String address = (String) params[7];


            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&pw=" + pw + "&name=" + name + "&gender="+gender+"&birth=" + birth
                    + "&phoneNum=" + phoneNum + "&address=" + address;


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

    //전화번호 인증
    public void sendVerificationCode() {
        String phoneNumber = enterPhoneNumber.getText().toString();

        if (phoneNumber.isEmpty()) {
            enterAutNumber.setError("enter your phone number");
            enterAutNumber.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };

    public void verifySignInCode() {
        String code = enterAutNumber.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithCredential:success");
                    Toast.makeText(AssignEmployee.this, "Successful", Toast.LENGTH_SHORT).show();
//                    FirebaseUser user = task.getResult().getUser();
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(AssignEmployee.this, "wrong verification code", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}