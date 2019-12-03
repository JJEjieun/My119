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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterNotice extends AppCompatActivity {
    private static final String TAG = "notice";

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3of1, adspin3of2;
    private EditText storename;
    private EditText pay;
    private EditText date;
    private EditText endtime;
    private Spinner key1, key2,key3_1, key3_2;
    private RadioButton cash, account;
    private RadioButton ftof, voice, face;

    static String k1,k2,k3_1,k3_2;
    private static String IP_ADDRESS = "10.0.2.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_notice);

        storename = (EditText)findViewById(R.id.r_storeName);
        pay = (EditText)findViewById(R.id.r_money);
        date = (EditText)findViewById(R.id.r_workDate);
        endtime = (EditText)findViewById(R.id.r_endTime);
        key1 = (Spinner)findViewById(R.id.enterKey1);
        key2 = (Spinner)findViewById(R.id.enterKey2);
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

                key1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        k1=parent.getItemAtPosition(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                key2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        k1=parent.getItemAtPosition(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                key3_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        k3_1=parent.getItemAtPosition(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                key3_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        k3_2=parent.getItemAtPosition(position).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                time = k3_1+k3_2;

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/notice.php",
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


                Toast.makeText(getApplicationContext(), "공고가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //스피너처리
        final Spinner spin1 = (Spinner)findViewById(R.id.enterKey1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterKey2);
        final Spinner spin3of1 = (Spinner)findViewById(R.id.enterKey3of1);
        final Spinner spin3of2 = (Spinner)findViewById(R.id.enterKey3of2);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.keySpin1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin2 = ArrayAdapter.createFromResource(this, R.array.keySpin2,
                android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin3of1 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of1.setAdapter(adspin3of1);
        spin3of1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin3of2 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of2.setAdapter(adspin3of2);
        spin3of2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

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
            String howTo = (String) params[7];
            String interview = (String) params[7];


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

}
