package com.example.my119;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private PaintView paintView;

    boolean passID;

    static String add1, add2, add3;

    private static String IP_ADDRESS = "10.0.2.2";

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
    private Button button_clear;
    Button e_save;
    Button assignButton;
    Uri su;

    Uri image;
    String signUri;
    Context context;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employee);


        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "외부 저장소 사용을 위해 읽기/쓰기 필요", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    2);  //마지막 인자는 체크해야될 권한 갯수

        } else {
            //Toast.makeText(this, "권한 승인되었음", Toast.LENGTH_SHORT).show();
        }


        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        paintView.setDrawingCacheEnabled(true);

        button_clear = (Button) findViewById(R.id.clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });

        signUri = String.valueOf(image);

        fbAuth = FirebaseAuth.getInstance();

        mEditTextID = (EditText) findViewById(R.id.enterID);
        mEditTextPW = (EditText) findViewById(R.id.enterPW);
        mEditTextName = (EditText) findViewById(R.id.enterName);
        mEditTextBirth = (EditText) findViewById(R.id.enterBornDate);
        //mEditTextAddress = (EditText) findViewById(R.id.enterAddress4);
        mEditTextPhone = (EditText) findViewById(R.id.enterPhoneNumber);
        femaleButton = (RadioButton) findViewById(R.id.enterGender1);
        maleButton = (RadioButton) findViewById(R.id.enterGender2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        address1 = (Spinner) findViewById(R.id.enterAddress1);
        address2 = (Spinner) findViewById(R.id.enterAddress2);
        address3 = (Spinner) findViewById(R.id.enterAddress3);
        check = (Button) findViewById(R.id.checkID);

        enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
        enterAutNumber = findViewById(R.id.enterAutNumber);
        checkPhoneNumber = findViewById(R.id.checkPhoneNumber);
        checkAutNumber = findViewById(R.id.checkAutNumber);

        final TextView textAut = (TextView) findViewById(R.id.textAut);

        //인증번호 받기 누르면 인증번호 입력하고 확인하는 버튼 생김
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

        e_save = findViewById(R.id.e_save);

        e_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenShot(paintView);
                String s1 = "";
                s1 = su.toString();
                signUri = s1;
                Toast.makeText(AssignEmployee.this, s1, Toast.LENGTH_SHORT).show();

            }
        });

//스피너에 주소 할당
        final Spinner spin1 = (Spinner) findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner) findViewById(R.id.enterAddress2);
        final Spinner spin3 = (Spinner) findViewById(R.id.enterAddress3);
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
                            if (Objects.equals(adspin2.getItem(j), "강남구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangnam, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "강동구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "강북구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "강서구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangseo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "관악구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gwanak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "광진구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangjin, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "구로구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_guro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "금천구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_geumcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "노원구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_nowon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "도봉구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_dobong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "동대문구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_dongdamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "동작구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_dongjak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "마포구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_mapo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "서대문구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_seodamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "서초구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_seocho, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "성동구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "성북구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_seongbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "송파구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_songpa, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "양천구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_yangcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "영등포구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "용산구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_yongsan, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "은평구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_eunpyeong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "종로구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_jongro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "중구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_joong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else if (Objects.equals(adspin2.getItem(j), "중랑구")) {
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployee.this,
                                        R.array.spinner3_joonglang, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            } else {
                                spin3.setAdapter(null);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else {
                    spin2.setAdapter(null);
                    spin3.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < Login.employeeinfos.size(); i++) {
                    String s = mEditTextID.getText().toString();
                    if (s.equals(Login.employeeinfos.get(i).getID())) {
                        Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다. ", Toast.LENGTH_SHORT).show();
                        passID = false;
                        break;
                    }
                    passID = true;
                }
            }
        });

        boolean allWrite = false;
        if (!(mEditTextID.getText().equals(null)) && !(mEditTextPW.getText().equals(null)) &&
                !(mEditTextName.getText().equals(null)) && !(mEditTextBirth.getText().equals(null)) &&
                !(mEditTextPhone.getText().equals(null)) && (radioGroup.isSelected()) &&
                (address1.isSelected()) && (address2.isSelected()) && (address3.isSelected()) && passID) {
            allWrite = true;
        }
        assignButton = (Button) findViewById(R.id.assignButton);
        // 위의 항목들이 모두 정상적으로 처리 되었으면
        // '등록' 버튼 누르면 로그인창으로 돌아감
//        if (allWrite) {
            assignButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String id = mEditTextID.getText().toString();
                    String pw = mEditTextPW.getText().toString();
                    String name = mEditTextName.getText().toString();
                    String addr = "";
                    String phoneNum = mEditTextPhone.getText().toString();
                    String birth = mEditTextBirth.getText().toString();
                    String gender = "";
                    String sign="";
                    if (femaleButton.isChecked()) {
                        gender = "여자";
                    } else if (maleButton.isChecked()) {
                        gender = "남자";
                    }

                    add1 = spin1.getSelectedItem().toString();
                    add2 = spin2.getSelectedItem().toString();
                    add3 = spin3.getSelectedItem().toString();

                    addr = add1
                            + " " + add2 + " " + add3
                    ;


                    sign = signUri;
                    AssignEmployee.InsertData task = new AssignEmployee.InsertData();
                    task.execute("http://" + IP_ADDRESS + "/assignEmployee.php",
                            id, pw, name, birth, gender, phoneNum, addr, sign);

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
//    }

//개인회원정보 데이터베이스에 저장
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
            String sign = (String) params[8];

            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&pw=" + pw + "&name=" + name + "&gender=" + gender + "&birth=" + birth
                    + "&phoneNum=" + phoneNum + "&address=" + address + "&sign=" + sign;


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

    //화면 캡쳐하기
    public File ScreenShot(View view) {
        view.setDrawingCacheEnabled(true);  //화면에 뿌릴때 캐시를 사용하게 한다

        Bitmap screenBitmap = view.getDrawingCache();   //캐시를 비트맵으로 변환

        String filename = "screenshotE.png";
        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures", filename);  //Pictures폴더 screenshotE.png 파일
        FileOutputStream os = null;

        su = Uri.fromFile(file);

        try {
            os = new FileOutputStream(file);
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);   //비트맵을 PNG파일로 변환
            os.close();


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        view.setDrawingCacheEnabled(false);
        return file;
    }

}