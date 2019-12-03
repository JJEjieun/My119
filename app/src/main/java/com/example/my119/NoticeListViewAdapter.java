package com.example.my119;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NoticeListViewAdapter extends BaseAdapter {
    private ArrayList<Notice> noticeArrayList = new ArrayList<Notice>();
    public NoticeListViewAdapter(){ }

    @Override
    public int getCount(){
        return noticeArrayList.size();
    }

    //여기서 리스트뷰에 데이터를 넣어줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //position은 ListView의 위치
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.notice_list, parent, false);
        }

        TextView workDate = (TextView) convertView.findViewById(R.id.db_workDate);
        TextView workTime = (TextView) convertView.findViewById(R.id.db_workTime);
        TextView storeName = (TextView) convertView.findViewById(R.id.db_storeName);
        TextView noticeNum = (TextView) convertView.findViewById(R.id.db_noticeNum);
        TextView endTime = (TextView) convertView.findViewById(R.id.db_endTime);
        TextView money = (TextView) convertView.findViewById(R.id.db_money);

        Notice listViewItem = noticeArrayList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        workDate.setText(listViewItem.getWorkDate());
        workTime.setText(listViewItem.getWorkTime());
        storeName.setText(listViewItem.getStore());
        noticeNum.setText(listViewItem.getNoticeNum());
        endTime.setText(listViewItem.getEndTime());
        money.setText(listViewItem.getMoney());

        //리스트뷰 클릭 이벤트
//        convertView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//
//            }
//        });

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return noticeArrayList.get(position);
    }

    //데이터 값 넣어줌
    public void addNotice(String workD, String workT, String storeN, String noticeNum, String endT, String money){
        Notice item = new Notice();

        item.setWorkDate(workD);
        item.setWorkTime(workT);
        item.setStore(storeN);
        item.setNoticeNum(noticeNum);
        item.setEndTime(endT);
        item.setMoney(money);

        noticeArrayList.add(item);
    }
}

