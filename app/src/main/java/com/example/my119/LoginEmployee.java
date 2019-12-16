package com.example.my119;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

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

public class LoginEmployee extends AppCompatActivity {

    private static String IP_ADDRESS = "10.0.2.2";

    String url = "http://"+ IP_ADDRESS +"/login_employee.php";
    String url2 = "http://" + IP_ADDRESS+ "/token_register.php";
    private static final String TAG = "tag";
//    public GettingPHP gphp;

    private EditText enterPw;
    private EditText enterId;
    static String newToken;
    static int erate;
    Context context;
//    public ArrayList<Employeeinfo> employeeinfos = new ArrayList<>();

    public static String eID, ePW, eName, eGender, eBirth, eAddress, ePhoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employee);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginEmployee.this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        newToken = instanceIdResult.getToken();
                        Log.e("newToken", newToken);
                    }
                });


        // true부분을 (ID PW 검사해서 맞으면)으로 바꿔야함.
        // '로그인'버튼 누르면 개인회원 메인페이지로 넘어감.

        context = this;
        enterId = (EditText) findViewById(R.id.id);
        enterPw = (EditText) findViewById(R.id.pw);

//        gphp = new GettingPHP();
//        gphp.execute(url);


        Button button_employee = (Button) findViewById(R.id.button_employee);
        button_employee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sid = enterId.getText().toString();
                PreferenceUtil.setPreferences(context, "pref", eID);
                Log.d("PreferencesUtil", "id: " + eID);
                PreferenceUtil.setString(context, "id", eID);

                boolean b = false;

                for (int i = 0; i < employeeinfos.size(); i++) {
                    if (employeeinfos.get(i).getID().equals(sid)) {
                        eID = employeeinfos.get(i).getID();
                        ePW = employeeinfos.get(i).getPW();
                        eName = employeeinfos.get(i).getName();
                        eGender = employeeinfos.get(i).getGender();
                        eBirth = employeeinfos.get(i).getBirth();
                        ePhoneNum = employeeinfos.get(i).getPhoneNum();
                        eAddress = employeeinfos.get(i).getAddress();
                        erate=employeeinfos.get(i).getRate();
                        Toast.makeText(getApplicationContext(), "개인회원 메인창", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainEmployee.class);
                        startActivity(intent);
                        finish();
                    }

                    InsertData task = new InsertData();
                    task.execute(url2, eID, newToken);
//                        else if(!employeeinfos.get(i).getID().equals(sid)) {
//                            Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_SHORT).show();
//                        }
                }
                if (!sid.equals(eID))
                    Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_SHORT).show();

            }
        });




        //'개인회원가입'텍스트 클릭 시 개인회원가입 창으로 넘어감
        TextView goto_assign = (TextView) findViewById(R.id.goto_assign);
        goto_assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인회원가입", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssignEmployee.class);
                startActivity(intent);
            }
        });
    }

//    class GettingPHP extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            StringBuilder jsonHtml = new StringBuilder();
//            try {
//                URL phpUrl = new URL(params[0]);
//                HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();
//                if (conn != null) {
//                    conn.setConnectTimeout(10000);
//                    conn.setUseCaches(false);
//                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//
//                        while (true) {
//                            String line = br.readLine();
//                            if (line == null) break;
//                            jsonHtml.append(line + "\n");
//                        }
//                        br.close();
//                    }
//                    conn.disconnect();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return jsonHtml.toString();
//        }
//
//        protected void onPostExecute(String str) {
//            try {
//                JSONObject jsonObject = new JSONObject(str);
//                JSONArray results = jsonObject.getJSONArray("webnautes");
//
//                for (int i = 0; i < results.length(); i++) {
//                    JSONObject temp = results.getJSONObject(i);
//                    employeeinfos.add(i, new Employeeinfo((String) temp.get("id"), (String) temp.get("pw"), (String) temp.get("name"), (String) temp.get("gender"), (String) temp.get("birth"), (String) temp.get("phoneNum"), (String) temp.get("address"),Integer.valueOf((String)temp.get("rate"))));
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(LoginEmployee.this,
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
            String token = (String) params[2];


            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&token=" + token;


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