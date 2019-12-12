package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import static com.example.my119.Login.employeeinfos;

public class MyPageEmployer extends AppCompatActivity {
    EditText employeeID;
    Button findButton;
    Button addButton;
    TextView employerName;

    String id;

    static String TAG = "frends";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_employer);

        employeeID= (EditText)findViewById(R.id.employee_id);
        findButton = (Button)findViewById(R.id.employee_search);
        addButton = (Button)findViewById(R.id.employee_add);
        employerName = (TextView)findViewById(R.id.mypageName);

        employerName.setText(LoginEmployer.rName+"님");

        Button btnResume = (Button)findViewById(R.id.resume);
        btnResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Resume.class);
                startActivity(intent);
            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                id = employeeID.getText().toString();
                for(int i =0; i<employeeinfos.size();i++){
                    if(id.equals(employeeinfos.get(i).getID())){
                        check = true;
                        Toast.makeText(getApplicationContext(), "존재하는 회원입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if(check == false){
                    Toast.makeText(getApplicationContext(), "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employerID = LoginEmployer.rID;
                String employeeID = id;


                InsertFriends task = new InsertFriends();
                task.execute("http://10.50.96.112/friends_register.php",employerID,employeeID);
                Toast.makeText(getApplicationContext(), "친구가 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    class InsertFriends extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MyPageEmployer.this,
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

            String boss = (String) params[1];
            String person = (String) params[2];



            String serverURL = (String) params[0];
            String postParameters = "boss=" + boss+ "&person=" + person;


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
