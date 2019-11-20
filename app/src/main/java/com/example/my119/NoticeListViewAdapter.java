package com.example.my119;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        TextView noticeName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView endTime = (TextView) convertView.findViewById(R.id.db_endTime);
        TextView storeName = (TextView) convertView.findViewById(R.id.db_storeName);
        TextView money = (TextView) convertView.findViewById(R.id.db_money);

        Notice listViewItem = noticeArrayList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        noticeName.setText(listViewItem.getNoticeName());
        endTime.setText(listViewItem.getEndTime());
        storeName.setText(listViewItem.getStore());
        money.setText(listViewItem.getMoney());

        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){}
        });

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
    public void addNotice(String noticeN, String endT, String storeN, String money){
        Notice item = new Notice();

        item.setNoticeName(noticeN);
        item.setEndTime(endT);
        item.setStore(storeN);
        item.setMoney(money);

        noticeArrayList.add(item);
    }

}

