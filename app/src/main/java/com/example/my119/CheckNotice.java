package com.example.my119;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static com.example.my119.Login.noticeinfos;

//import static com.example.my119.RegisterNotice.noticeinfos;



public class CheckNotice extends AppCompatActivity {
    ArrayList<Integer> noticeNumList = new ArrayList<Integer>();
    ArrayList<Notice> noticeList = new ArrayList<Notice>();

//    public GetPHP p;
//    String url ="http://10.0.2.2/login_notice.php";
    static  int num;
    ArrayList<String> notices = new ArrayList<>();
    String storeName, pay,  date,  endtime, key1, key2,  key3,  paymethod, interview;
//    public static ArrayList<Noticeinfo> noticeinfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

            //새 공고 등록 버튼 누르면 공고 등록 창으로 넘어감. 공고 등록하고 메인으로 돌아감
            Button btnNewNotice = (Button)findViewById(R.id.newNotice);
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
        final Noticeinfo noticeAdapter = new Noticeinfo(storeName, pay, date, endtime, key1,key2,key3, paymethod,interview);

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listView1);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();
                notices.add(noticeinfos.get(position).getStoreName());
                notices.add(noticeinfos.get(position).getPay());
                notices.add(noticeinfos.get(position).getDate());
                notices.add(noticeinfos.get(position).getEndtime());
                notices.add(noticeinfos.get(position).getKey1());
                notices.add(noticeinfos.get(position).getKey2());
                notices.add(noticeinfos.get(position).getKey3());
                notices.add(noticeinfos.get(position).getPaymethod());
                notices.add(noticeinfos.get(position).getInterview());

//                String noticeNum = ((Notice)adapter.getItem(position)).getNoticeNum();
//                Bundle list_bundle = new Bundle();
//                list_bundle.putStringArrayList("noticeInfo",notices);
                Intent intent = new Intent(CheckNotice.this, ShowNotice.class);

                intent.putStringArrayListExtra("noticeInfo",notices);
//                intent.putExtra("noticeNum", noticeNum);
                startActivity(intent);
            }
        });

    }

    private void setData(NoticeListViewAdapter adapter) {

        for(int i = noticeinfos.size()-1; i >= 0;i--){
            int count = 0;
            num = noticeinfos.size()- count;
            adapter.addNotice(noticeinfos.get(i).getDate(), noticeinfos.get(i).getKey3(), noticeinfos.get(i).getStoreName(),
                    String.valueOf(num), noticeinfos.get(i).getEndtime(), noticeinfos.get(i).getPay());
            count++;
        }
    }



}


