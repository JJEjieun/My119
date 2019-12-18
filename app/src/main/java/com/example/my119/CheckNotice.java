package com.example.my119;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.noticeinfos;

//import static com.example.my119.RegisterNotice.noticeinfos;



public class CheckNotice extends AppCompatActivity {
    ArrayList<Integer> noticeNumList = new ArrayList<Integer>();
    ArrayList<Notice> noticeList = new ArrayList<Notice>();

//    public GetPHP p;
//    String url ="http://10.0.2.2/login_notice.php";
    static  int numApply;
    int num;
    ArrayList<String> notices = new ArrayList<>();
    public String[] notice = new String[10];
    String storeName, pay,  date,  endtime, key1, key2,  key3,  paymethod, interview;
//    public static ArrayList<Noticeinfo> noticeinfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

            //새 공고 등록 버튼 누르면 공고 등록 창으로 넘어감. 공고 등록하고 메인으로 돌아감
            Button btnNewNotice = (Button)findViewById(R.id.newNotice);
        btnNewNotice.setVisibility(View.INVISIBLE);

        for(int i =0; i < Login.friendInfos.size();i++){
            if(Login.friendInfos.get(i).getPerson().equals(LoginEmployee.eID)){
                btnNewNotice.setVisibility(View.VISIBLE);
            }
        }

            btnNewNotice.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "새 공고 등록", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RegisterNotice.class);
                    startActivity(intent);
                    finish();
                }
            });
//        p = new GetPHP();
//        p.execute(url);

        // Adapter 생성
        final NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;
        final Noticeinfo noticeAdapter = new Noticeinfo(storeName, pay, date, endtime, key1,key2,key3, paymethod,interview,String.valueOf(num));

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();

                notice[0] =noticeinfos.get(noticeinfos.size()-position-1).getStoreName();
                notice[1] =noticeinfos.get(noticeinfos.size()-position-1).getPay();
                notice[2] =noticeinfos.get(noticeinfos.size()-position-1).getDate();
                notice[3] =noticeinfos.get(noticeinfos.size()-position-1).getEndtime();
                notice[4] =noticeinfos.get(noticeinfos.size()-position-1).getKey1();
                notice[5] =noticeinfos.get(noticeinfos.size()-position-1).getKey2();
                notice[6] =noticeinfos.get(noticeinfos.size()-position-1).getKey3();
                notice[7] =noticeinfos.get(noticeinfos.size()-position-1).getPaymethod();
                notice[8] = noticeinfos.get(noticeinfos.size()-position-1).getInterview();
                notice[9]= String.valueOf(noticeinfos.size()-position-1);


//                String noticeNum = ((Notice)adapter.getItem(position)).getNoticeNum();
//                Bundle list_bundle = new Bundle();
//                list_bundle.putStringArrayList("noticeInfo",notices);
                Intent intent = new Intent(CheckNotice.this, ShowNotice.class);

                intent.putExtra("noticeInfo",notice);
//                intent.putExtra("noticeNum", noticeNum);
                startActivity(intent);
            }
        });

    }

    private void setData(NoticeListViewAdapter adapter) {

        for(int i = noticeinfos.size()-1; i >= 0;i--){
            int count = 0;
            num = i+1;
            noticeinfos.get(i).setNum(String.valueOf(num));
            numApply=Integer.valueOf(noticeinfos.get(i).getNum());
            adapter.addNotice(noticeinfos.get(i).getDate(), noticeinfos.get(i).getKey3(), noticeinfos.get(i).getStoreName(),
                    noticeinfos.get(i).getNum(), noticeinfos.get(i).getEndtime(), noticeinfos.get(i).getPay());
            count++;
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


