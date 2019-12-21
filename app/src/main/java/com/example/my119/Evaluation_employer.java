package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.my119.LoginEmployer.r_rate;

public class Evaluation_employer extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";
    private static final String TAG = "rate";
    int num;
    String employer_name;

    static double point=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_employer);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("근무지평가");

        Intent intent = getIntent();
        final String er_apply =intent.getStringExtra("er_apply");

        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //데이터베이스에서 기존 별점을 가져오고, 입력한 별점을 더하여 구한 최종 별점
                //최근 평가의 결과의 비중을 좀 더 높에 넣어 최근 데이터를 강조
                    num=Integer.valueOf(Login.applyinfos.get(Integer.valueOf(er_apply)).getNum())-1;
                    employer_name =Login.employerinfos.get(num).getName();
                    double rate = Double.valueOf(Login.employerinfos.get(num).getRate());
                    point = ( rate+ rating) / 2;

            }


        });
        Button btn_ev_ee = (Button)findViewById(R.id.r_ev_ee);
        btn_ev_ee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업평가 등록", Toast.LENGTH_SHORT).show();
                //평가 디비에 저장
                InsertData task = new InsertData();
                task.execute("http://" +IP_ADDRESS+ "/get_er_rate.php",Login.employerinfos.get(num).getID(),String.valueOf(point));
                finish();
            }
        });

    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Evaluation_employer.this,
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
            String rate= (String) params[2];


            String serverURL = (String) params[0];
            String postParameters = "id="+id+"&rate=" + rate ;

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
