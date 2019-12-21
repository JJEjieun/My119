package com.example.my119;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
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

import static com.example.my119.Login.employeeinfos;

public class MyPageEmployer extends AppCompatActivity {
    EditText employeeID;
    Button findButton;
    Button addButton;
    TextView employerName;
    TextView tv;

    String fid = "";
    String fname = "";


    String id;

    public final static String ip="10.50.96.112";

    ArrayList<String> friendList = new ArrayList<>();
    static String TAG = "frends";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_employer);

        employeeID= (EditText)findViewById(R.id.employee_id);
        findButton = (Button)findViewById(R.id.employee_search);
        addButton = (Button)findViewById(R.id.employee_add);
        employerName = (TextView)findViewById(R.id.mypageName);

        tv=(TextView)findViewById(R.id.rateText);
        tv.setText(String.valueOf((Float.valueOf(LoginEmployer.r_rate))));

        RatingBar rb = (RatingBar)findViewById(R.id.ratingbar);
        rb.setRating(Math.round(Float.valueOf(LoginEmployer.r_rate)));

        employerName.setText(LoginEmployer.rName+"님");

        Button button_supply = (Button)findViewById(R.id.contract);
        button_supply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "근로 계약서", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Contract_employer.class);
                startActivity(intent2);
            }
        });

        Button button_requestedNotice = (Button)findViewById(R.id.requestednotice);
        button_requestedNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청받은 공고 확인", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), RequestedNoticeList.class);
                startActivity(intent3);
                finish();
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

                //기업과 개인이 친구를 맺음
                InsertFriends task = new InsertFriends();
                task.execute("http://"+ ip +"/friends_register.php",employerID,employeeID);
                Toast.makeText(getApplicationContext(), "친구가 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        // Adapter 생성
        final FriendAdapter adapter = new FriendAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.lv_friend_mypage_employer);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

    }

    private void setData(FriendAdapter adapter) {

        for (int i = 0; i < Login.friendInfos.size(); i++) {
            if (Login.friendInfos.get(i).getBoss().equals(LoginEmployer.rID)) {
                fid = Login.friendInfos.get(i).getPerson();
                for (int j = 0; j < employeeinfos.size(); j++) {
                    if (employeeinfos.get(j).getID().equals(fid)) {
                        fname = employeeinfos.get(j).getName();
                        adapter.addFriends(fid, fname,LoginEmployer.rCompanyName);
                    }
                }
            }
        }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mainPage:
                setContentView(R.layout.main_employee);
                return true;
            case R.id.resume:
                setContentView(R.layout.resume);
                return true;
            case R.id.contract:
                setContentView(R.layout.contract_employee);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
