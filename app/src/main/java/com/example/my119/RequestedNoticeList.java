package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.noticeinfos;
import static com.example.my119.Login.applyinfos;


public class RequestedNoticeList extends AppCompatActivity {

    ArrayList<String> notices = new ArrayList<>();
    int applyNum;
    public String[] notice = new String[11];
    int n = 0;
    int ee_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_notice_list);

        final NoticeListViewAdapter adapter = new NoticeListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        ListView listview = (ListView) findViewById(R.id.listview);

        // 리스트뷰 값 받아오기
        setData(adapter);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();

                notice[0] =adapter.getStore(position);
                notice[1] =adapter.getMoney(position);
                notice[2] =adapter.getWorkDate(position);
                notice[3] =adapter.getEndTime(position);
                notice[4] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))-1).getKey1();
                notice[5] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))-1).getKey2();
                notice[6] =adapter.getWorkTime(position);
                notice[7] =noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))-1).getPaymethod();
                notice[8] = noticeinfos.get(Integer.valueOf(adapter.getNoticeNum(position))-1).getInterview();
                notice[9]= adapter.getStore(position);
                notice[10]=String.valueOf(ee_apply);

                Intent intent = new Intent(RequestedNoticeList.this, RequestedNotice.class);

                intent.putExtra("noticeInfos",notice);
                startActivity(intent);
            }
        });
    }

    private void setData(NoticeListViewAdapter adapter) {
        //리스트뷰에 띄울 데이터 추가
        for(int i =0; i< applyinfos.size(); i++){
             n =Integer.valueOf(applyinfos.get(i).getNum())-1;
             ee_apply = i;
                if (noticeinfos.get(n).getStoreName().equals(LoginEmployer.rCompanyName)) {
                   // applyNum = Integer.valueOf(applyinfos.get(i).getNum());
                    adapter.addNotice(noticeinfos.get(n).getDate(), noticeinfos.get(n).getKey3(), noticeinfos.get(n).getStoreName(),
                            noticeinfos.get(n).getNum(), noticeinfos.get(n).getEndtime(), noticeinfos.get(n).getPay());

            }
        }
    }

}