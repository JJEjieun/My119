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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.my119.LoginEmployee.erate;

public class Evaluation_employee extends AppCompatActivity {
    static double point=0;
    static String TAG = "evaluation_employee";

    TextView tv;
    String employee_name;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_employee);

        Intent intent = getIntent();
        final String ee_apply =intent.getStringExtra("ee_apply");

        tv = (TextView)findViewById(R.id.textView2) ;
        tv.setText(employee_name);

        RatingBar rb = (RatingBar)findViewById(R.id.ratingBar);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                for(int i=0; i< Login.employeeinfos.size(); i++){
                    if(Login.applyinfos.get(Integer.valueOf(ee_apply)).getEid().equals(Login.employeeinfos.get(i).getID())){
                        num=i;
                        employee_name=Login.employeeinfos.get(i).getName();
                        tv.setText(employee_name);
                        double rate = Double.valueOf(Login.employeeinfos.get(i).getRate());
                        point = ( rate+ rating) / 2;
                    }
                }
            }
        });



        Button btn_ev_er = (Button)findViewById(R.id.r_ev_er);
        btn_ev_er.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),String.valueOf(point), Toast.LENGTH_SHORT).show();
                InsertData task = new InsertData();
                task.execute("http://" +"10.0.2.2"+ "/get_ee_rate.php",Login.employeeinfos.get(num).getID(),String.valueOf(point));
                finish();
            }
        });

    }
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Evaluation_employee.this,
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
