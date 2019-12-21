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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class RegisterNotice extends AppCompatActivity {
    private static final String TAG = "notice";

    public GettingPhp p;
    String url = "http://10.0.2.2/fcmpush.php";


    ArrayAdapter<CharSequence> adspin1, adspin0, adspin2, adspin3, adspin3of1, adspin3of2;
    private EditText storename;
    private EditText pay;
    private EditText date;
    private EditText endtime;
    private Spinner key1, key0, key2, key3, key3_1, key3_2;
    private RadioButton cash, account;
    private RadioButton ftof, voice, face;


//    static String storeName, money,work, end,keyone, keytwo, keythree, howtopay, howtointerview;
    static String k1,k0,k2,k3,k3_1,k3_2;
    private static String IP_ADDRESS = "10.0.2.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_notice);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("공고등록");

        storename = (EditText)findViewById(R.id.r_storeName);
        pay = (EditText)findViewById(R.id.r_money);
        date = (EditText)findViewById(R.id.r_workDate);
        endtime = (EditText)findViewById(R.id.r_endTime);
        key1 = (Spinner)findViewById(R.id.enterKey1);
        key0 = (Spinner)findViewById(R.id.enterAddress1);
        key2 = (Spinner)findViewById(R.id.enterAddress2);
        key3 = (Spinner)findViewById(R.id.enterAddress3);
        key3_1 = (Spinner)findViewById(R.id.enterKey3of1);
        key3_2=(Spinner)findViewById(R.id.enterKey3of2);
        cash = (RadioButton)findViewById(R.id.cash);
        account = (RadioButton)findViewById(R.id.account);
        ftof = (RadioButton)findViewById(R.id.fTof);
        voice = (RadioButton)findViewById(R.id.call);
        face = (RadioButton)findViewById(R.id.fCall);

        //공고 등록 버튼 누르면 공고 등록 됨
        /**/    //데베 작업 필요
        Button btnNewNotice = (Button)findViewById(R.id.btn_register);
        btnNewNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = storename.getText().toString();
                String payment = pay.getText().toString();
                String workdate = date.getText().toString();
                String end = endtime.getText().toString();
                String time ="";
                String howto="";
                String interview="";

                if(cash.isChecked()){
                    howto="현금지급";
                }else if(account.isChecked()){
                    howto="계좌지급";
                }

                if(voice.isChecked()){
                    interview="전화통화";
                }else if(face.isChecked()){
                    interview="화상통화";
                }else if (ftof.isChecked()){
                    interview="면대면";
                }

                k1 = key1.getSelectedItem().toString();
                k0 = key0.getSelectedItem().toString();
                k2 = key2.getSelectedItem().toString();
                k3 = key3.getSelectedItem().toString();
                k3_1 = key3_1.getSelectedItem().toString();
                k3_2 = key3_2.getSelectedItem().toString();

                time = k3_1+" "+k3_2;

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS  + "/notice.php",
                        name, payment, workdate, end, k1,k2,time,howto,interview);




                if (storename.length() > 0) {
                    storename.getText().clear();
                }
                if (pay.length() > 0) {
                    pay.getText().clear();
                }
                if (date.length() > 0) {
                    date.getText().clear();
                }
                if (endtime.length() > 0) {
                    endtime.getText().clear();
                }
                p = new GettingPhp();
                p.execute(url);

                Toast.makeText(getApplicationContext(), "공고가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });




        //스피너처리
        final Spinner spin1 = (Spinner)findViewById(R.id.enterKey1);
        final Spinner spin3of1 = (Spinner)findViewById(R.id.enterKey3of1);
        final Spinner spin3of2 = (Spinner)findViewById(R.id.enterKey3of2);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.keySpin1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);


        //스피너에 주소 입력.
        final Spinner spin0 = (Spinner)findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterAddress2);
        final Spinner spin3 = (Spinner)findViewById(R.id.enterAddress3);
        adspin0 = ArrayAdapter.createFromResource(this, R.array.spinner1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin0.setAdapter(adspin0);
        spin0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin0.getItem(i), "서울")) {
                    adspin2 = ArrayAdapter.createFromResource(RegisterNotice.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin2.getItem(j),"강남구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangnam, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강동구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강북구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"강서구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangseo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"관악구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gwanak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"광진구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangjin, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"구로구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_guro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"금천구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_geumcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"노원구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_nowon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"도봉구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_dobong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"동대문구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_dongdamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"동작구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_dongjak, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"마포구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_mapo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"서대문구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_seodamoon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"서초구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_seocho, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"성동구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_gangdong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"성북구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_seongbook, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"송파구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_songpa, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"양천구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_yangcheon, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"영등포구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_yeongdeungpo, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"용산구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_yongsan, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"은평구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_eunpyeong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"종로구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_jongro, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"중구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
                                        R.array.spinner3_joong, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else if(Objects.equals(adspin2.getItem(j),"중랑구")) {
                                adspin3 = ArrayAdapter.createFromResource(RegisterNotice.this,
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


        adspin3of1 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of1.setAdapter(adspin3of1);

        adspin3of2 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of2.setAdapter(adspin3of2);


    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(RegisterNotice.this,
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

            String storename = (String) params[1];
            String payment = (String) params[2];
            String date = (String) params[3];
            String endtime = (String) params[4];
            String key1 = (String) params[5];
            String key2 = (String) params[6];
            String key3 = (String) params[7];
            String howTo = (String) params[8];
            String interview = (String) params[9];


            String serverURL = (String) params[0];
            String postParameters = "storeName=" + storename + "&pay=" + payment + "&date=" + date + "&endtime="+endtime+"&key1=" + key1
                    + "&key2=" + key2 + "&key3=" + key3+"&paymethod="+howTo+"&interview="+interview;


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

    class GettingPhp extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            StringBuilder jsonHtml = new StringBuilder();
            try {
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();
                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                        while (true) {
                            String line = br.readLine();
                            if (line == null) break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONArray results = jsonObject.getJSONArray("webnautes");

                for (int i = 0; i < results.length(); i++) {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}