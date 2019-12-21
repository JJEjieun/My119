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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.Objects;

import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.noticeinfos;

public class CheckNotice extends AppCompatActivity {

//    public GetPHP p;
//    String url ="http://10.0.2.2/login_notice.php";
    static  int numApply;
    ListView listview,listview2;
    int num;
    ArrayList<String> notices = new ArrayList<>();
    public String[] notice = new String[10];
    String storeName, pay,  date,  endtime, key1, key2,  key3,  paymethod, interview;
    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;
    String findString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_notice);

         listview = (ListView) findViewById(R.id.listView1);
         listview2=(ListView)findViewById(R.id.listView2);

        //분류별 공개
        final Spinner spin_show_category = (Spinner)findViewById(R.id.notice_show_category);
        final Spinner spin_show_address1 = (Spinner)findViewById(R.id.notice_show_address1);
        final Spinner spin_show_address2 = (Spinner)findViewById(R.id.notice_show_address2);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.keySpin1, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_show_category.setAdapter(adspin1);
        spin_show_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                findString = spin_show_category.getSelectedItem().toString();
             }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Adapter 생성
        final NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;
        final Noticeinfo noticeAdapter = new Noticeinfo(storeName, pay, date, endtime, key1,key2,key3, paymethod,interview,String.valueOf(num));

        // 리스트뷰 참조 및 Adapter달기


        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        adspin2 = ArrayAdapter.createFromResource(this, R.array.spinner1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_show_address1.setAdapter(adspin2);
        spin_show_address1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin2.getItem(i), "서울")) {
                    adspin3 = ArrayAdapter.createFromResource(CheckNotice.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin_show_address2.setAdapter(adspin3);
                    spin_show_address2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            findString = spin_show_category.getSelectedItem().toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin_show_address2.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        final NoticeListViewAdapter adapter2 = new NoticeListViewAdapter();
        Button btnFindNotice = (Button)findViewById(R.id.find_category);
        btnFindNotice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckNotice.this, findString, Toast.LENGTH_SHORT).show();
                //버튼누르면 findString이랑 같은 업종/동주소에 따라 바뀌게
                if(listview.getVisibility()==View.VISIBLE) {
                    listview.setVisibility(View.GONE);
                    listview2.setVisibility(View.VISIBLE);
                    setData2(adapter2);
                    listview2.setAdapter(adapter2);
                }
            }
        });

        Button btnShowRecent = (Button)findViewById(R.id.show_recent);
        btnShowRecent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckNotice.this, findString, Toast.LENGTH_SHORT).show();
                //버튼누르면 최신순으로
                if(listview2.getVisibility()==View.VISIBLE) {
                    listview2.setVisibility(View.GONE);
                    listview.setVisibility(View.VISIBLE);
                    setData(adapter);
                    listview.setAdapter(adapter);
                }
            }
        });


            //새 공고 등록 버튼 누르면 공고 등록 창으로 넘어감. 공고 등록하고 메인으로 돌아감
            Button btnNewNotice = (Button)findViewById(R.id.newNotice);
//        btnNewNotice.setVisibility(View.INVISIBLE);

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

    private void setData2(NoticeListViewAdapter adapter) {
        //검색 조건에 맞게 리스트뷰로 가져옴
        for(int i = noticeinfos.size()-1; i >= 0;i--){
            if (noticeinfos.get(i).getKey1().trim().equals(findString)) {
                Toast.makeText(getApplicationContext(),noticeinfos.get(i).getKey1()+"1",Toast.LENGTH_SHORT);
                int count = 0;
                num = i+1;
                noticeinfos.get(i).setNum(String.valueOf(num));
                numApply=Integer.valueOf(noticeinfos.get(i).getNum());
                adapter.addNotice(noticeinfos.get(i).getDate(), noticeinfos.get(i).getKey3(), noticeinfos.get(i).getStoreName(),
                        noticeinfos.get(i).getNum(), noticeinfos.get(i).getEndtime(), noticeinfos.get(i).getPay());
                count++;
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


