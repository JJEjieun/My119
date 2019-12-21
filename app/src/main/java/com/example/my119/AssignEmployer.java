package com.example.my119;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class AssignEmployer extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static String TAG = "phptest";

    static String add1,add2,add3;

    boolean passID, passNum;

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
    private Button button_clear;
    private PaintView paintView;
    private Button check;

    Button r_save;
    Uri su;
    String signUri;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;

    String docode, gucode;
    URL newURL;
    public static String s="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employer);

        TextView TorF = (TextView)findViewById(R.id.TorF);
        Button checkNum = (Button)findViewById(R.id.checkNumber);
        r_save = findViewById(R.id.r_save);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        paintView.setDrawingCacheEnabled(true);

        button_clear = (Button)findViewById(R.id.clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });

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
                    docode = "11";
                    adspin2 = ArrayAdapter.createFromResource(AssignEmployer.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin2.getItem(j),"강남구")) {
                                gucode = "680";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangnam, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강동구")) {
                                gucode = "740";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강북구")) {
                                gucode = "305";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강서구")) {
                                gucode = "500";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangseo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"관악구")) {
                                gucode = "620";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gwanak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"광진구")) {
                                gucode = "215";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangjin, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"구로구")) {
                                gucode = "530";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_guro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"금천구")) {
                                gucode = "545";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_geumcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"노원구")) {
                                gucode = "350";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_nowon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"도봉구")) {
                                gucode = "320";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_dobong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"동대문구")) {
                                gucode = "230";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_dongdamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"동작구")) {
                                gucode = "590";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_dongjak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"마포구")) {
                                gucode = "440";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_mapo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"서대문구")) {
                                gucode = "410";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_seodamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"서초구")) {
                                gucode = "650";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_seocho, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"성동구")) {
                                gucode = "120";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"성북구")) {
                                gucode = "290";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_seongbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"송파구")) {
                                gucode = "710";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_songpa, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"양천구")) {
                                gucode = "470";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_yangcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"영등포구")) {
                                gucode = "560";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"용산구")) {
                                gucode = "170";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_yongsan, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"은평구")) {
                                gucode = "380";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_eunpyeong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"종로구")) {
                                gucode = "110";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_jongro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"중구")) {
                                gucode = "140";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_joong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"중랑구")) {
                                gucode = "260";
                                adspin3 = ArrayAdapter.createFromResource(AssignEmployer.this,
                                        R.array.spinner3_joonglang, android.R.layout.simple_spinner_dropdown_item);
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
        check = (Button)findViewById(R.id.checkID);

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

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0;i< Login.employerinfos.size();i++) {
                    String s = mEditTextID.getText().toString();
                    if (s.equals(Login.employerinfos.get(i).getID())){
                        Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다. ", Toast.LENGTH_SHORT).show();
                        passID = false;
                        break;
                    }
                    passID = true;
                }
            }
        });

        checkNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        r_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenShot(paintView);
                String s1 = "";
                s1 = su.toString();
                signUri = s1;
                Toast.makeText(AssignEmployer.this, signUri, Toast.LENGTH_SHORT).show();

            }
        });

        boolean allWrite = false;
        if(!(mEditTextID.getText().equals(null)) && !(mEditTextPW.getText().equals(null)) && !(mEditTextNum.getText().equals(null)) &&
                !(mEditTextName.getText().equals(null)) && !(mEditTextStore.getText().equals(null)) && !(mEditTextAddress.getText().equals(null)) &&
                !(mEditTextPhone.getText().equals(null)) && !(mEditTextEmail.getText().equals(null)) &&
                (address1.isSelected()) && (address2.isSelected()) && (address3.isSelected()) && passID && passNum) {
            allWrite = true;
        }

        findViewById(R.id.checkNumber).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                s = "";
                final String u = "http://apis.data.go.kr/B552015/NpsBplcInfoInqireService/getBassInfoSearch?ldong_addr_mgpl_dg_cd=" + docode +
                        "&ldong_addr_mgpl_sggu_cd=" + gucode + "&wkpl_nm=" + mEditTextStore.getText() + "&bzowr_rgst_no=" + mEditTextNum.getText().toString().substring(0,6) +
                        "&pageNo=10&startPage=10&numOfRows=1&pageSize=1&serviceKey=" +
                        "lNgp1L%2FKCOrGBcrfflEu%2BIULQIaFSTlzqdtBzdfBl65sJUg55bM%2B4%2BHUlNU78DBQYWCC9emL90JJwM2vD9WnIA%3D%3D";

                new Thread() {
                    public void run() {
                        try {
                            newURL = new URL(u);
                            InputStream in = newURL.openStream();
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser();
                            parser.setInput(in, "utf-8");
                            String tag = "";
                            int EventType = parser.getEventType();
                            while (EventType != XmlPullParser.END_DOCUMENT) {
                                switch (EventType) {
                                    case XmlPullParser.START_TAG:
                                        tag = parser.getName();
                                        if (tag.equals("wkplJnngStcd")) {
                                        }
                                        break;
                                    case XmlPullParser.TEXT:
                                        if (tag.equals("wkplJnngStcd")) {
                                            if (parser.getText().equals("1"))
                                                s = parser.getText();
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        tag = parser.getName();
                                        if (tag.equals("item")) {
                                        }
                                        break;
                                }
                                EventType = parser.next();
                            }
                        } catch (Exception e) {
                            finish();
                        }
                    }
                }.start();

                try {
                    sleep(5000);
                    if (s.equals("1")) {
                        passNum = true;
                        Toast.makeText(AssignEmployer.this, "사업장이 확인되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        passNum = false;
                        Toast.makeText(AssignEmployer.this, "사업장이 확인되지않습니다.\n다시입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        if (allWrite) {
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

                    address = add1
                            +" "+add2+" "+add3+" "+ mEditTextAddress.getText().toString()
                    ;

                    String sign = "";

                    sign = signUri;

                    InsertData task = new InsertData();
                    task.execute("http://" + "10.0.2.2" + "/assignEmployer.php",
                            id, pw, employerNumber, companyName, name, address, phoneNum, email, sign);

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
//    }

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
            String sign = (String) params[9];

            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&pw=" + pw+ "&employerNumber=" +employerNumber+ "&companyName=" +companyName+ "&name=" +name
                    + "&address=" +address+ "&phoneNum=" +phoneNum+ "&email=" +email + "&sign=" +sign;


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
    //화면 캡쳐하기
    public File ScreenShot(View view) {
        view.setDrawingCacheEnabled(true);  //화면에 뿌릴때 캐시를 사용하게 한다

        Bitmap screenBitmap = view.getDrawingCache();   //캐시를 비트맵으로 변환

        String filename = "screenshotR.png";
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