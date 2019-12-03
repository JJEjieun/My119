package com.example.my119;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoginEmployee extends AppCompatActivity {

    String url ="http://10.0.2.2/login_employee.php";
    public GettingPHP gphp;

    private EditText enterPw;
    private EditText enterId;
    Context context;
    final ArrayList<Employeeinfo> employeeinfos = new ArrayList<>();

    static String eID, ePW, eName, eGender, eBirth, eAddress, ePhoneNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_employee);

        // true부분을 (ID PW 검사해서 맞으면)으로 바꿔야함.
        // '로그인'버튼 누르면 개인회원 메인페이지로 넘어감.

        context = this;
        enterId = (EditText)findViewById(R.id.id);
        enterPw = (EditText)findViewById(R.id.pw);

        gphp = new GettingPHP();
        gphp.execute(url);

            Button button_employee = (Button)findViewById(R.id.button_employee);
            button_employee.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String sid = enterId.getText().toString();
                    PreferenceUtil.setPreferences(context, "id", sid);
                    Log.d("PreferencesUtil", "id: " + sid);

                    for(int i=0; i<employeeinfos.size();i++){
                        if(employeeinfos.get(i).getID().equals(sid)){
                            Toast.makeText(getApplicationContext(), "개인회원 메인창", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainEmployee.class);
                            startActivity(intent);
                            finish();
                        }else
                            Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        //'개인회원가입'텍스트 클릭 시 개인회원가입 창으로 넘어감
        TextView goto_assign = (TextView)findViewById(R.id.goto_assign);
        goto_assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "개인회원가입", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AssignEmployee.class);
                startActivity(intent);
            }
        });
    }

    class GettingPHP extends AsyncTask<String, Integer,String> {
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

        protected void onPostExecute(String str){
            try{
                JSONObject jsonObject = new JSONObject(str);
                JSONArray results = jsonObject.getJSONArray("webnautes");

                for(int i =0; i<results.length();i++){
                    JSONObject temp = results.getJSONObject(i);
                    employeeinfos.add(i, new Employeeinfo((String)temp.get("id"),(String)temp.get("pw"),(String)temp.get("name"),(String)temp.get("gender"),(String)temp.get("birth"),(String)temp.get("phoneNum"),(String)temp.get("address")));
                    eID = (String)temp.get("id");
                    ePW = (String)temp.get("pw");
                    eName=(String)temp.get("name");
                    eGender = (String)temp.get("gender");
                    eBirth=(String)temp.get("birth");
                    ePhoneNum =(String)temp.get("phoneNum");
                    eAddress=(String)temp.get("address");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}