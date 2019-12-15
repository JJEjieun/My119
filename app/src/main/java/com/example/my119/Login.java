package com.example.my119;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Login extends AppCompatActivity {
    public final static String ip="10.0.2.2";

    public GetPHP p;
    String url = "http://"+ip+"/login_notice.php";
    public static ArrayList<Noticeinfo> noticeinfos = new ArrayList<>();

    String url1 ="http://"+ ip +"/login_employer.php";
    public static ArrayList<Employerinfo> employerinfos = new ArrayList<>();
    public GettingPHP1 gphp;

    String url2 = "http://"+ ip +"/login_employee.php";
    public static ArrayList<Employeeinfo> employeeinfos = new ArrayList<>();
    public GettingPHP2 g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        p = new GetPHP();
        p.execute(url);

        gphp = new GettingPHP1();
        gphp.execute(url1);

        g = new GettingPHP2();
        g.execute(url2);

        //'개인회원 로그인'버튼 클릭 시 개인회원 로그인 창으로 넘어감
        Button button_employee = (Button) findViewById(R.id.button_employee);
        button_employee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인회원 로그인", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginEmployee.class);
                startActivity(intent);
            }
        });

        //'기업회원 로그인'버튼 클릭 시 기업회원 로그인 창으로 넘어감
        Button button_employer = (Button) findViewById(R.id.button_employer);
        button_employer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "기업회원 로그인", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), LoginEmployer.class);
                startActivity(intent2);
            }
        });
    }

     class GetPHP extends AsyncTask<String, Integer, String> {
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
                    JSONObject temp = results.getJSONObject(i);
                    noticeinfos.add(i, new Noticeinfo((String) temp.get("storeName"), (String) temp.get("pay"),
                            (String) temp.get("date"), (String) temp.get("endtime"), (String) temp.get("key1"),
                            (String) temp.get("key2"), (String) temp.get("key3"), (String) temp.get("paymethod"),
                            (String) temp.get("interview")));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    class GettingPHP1 extends AsyncTask<String, Integer,String> {
        @Override
        protected String doInBackground(String...params){
            StringBuilder jsonHtml = new StringBuilder();
            try{
                URL phpUrl = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                        while(true){
                            String line = br.readLine();
                            if(line==null) break;
                            jsonHtml.append(line+"\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONArray results = jsonObject.getJSONArray("webnautes");

                String zz = "";
                for (int i = 0; i < results.length(); i++) {
                    JSONObject temp = results.getJSONObject(i);
                    employerinfos.add(i, new Employerinfo((String) temp.get("id"),
                            (String) temp.get("pw"),(String) temp.get("employerNumber"),
                            (String) temp.get("companyName"),(String) temp.get("name"),
                            (String) temp.get("address"),(String) temp.get("phoneNum"),
                            (String) temp.get("email"),Integer.valueOf((String)temp.get("rate"))));


                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    class GettingPHP2 extends AsyncTask<String, Integer, String> {
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
                    JSONObject temp = results.getJSONObject(i);
                    employeeinfos.add(i, new Employeeinfo((String) temp.get("id"),
                            (String) temp.get("pw"), (String) temp.get("name"),
                            (String) temp.get("gender"), (String) temp.get("birth"),
                            (String) temp.get("phoneNum"), (String) temp.get("address"),
                            Integer.valueOf((String)temp.get("rate"))));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




}
