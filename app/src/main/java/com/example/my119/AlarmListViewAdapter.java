package com.example.my119;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AlarmListViewAdapter extends BaseAdapter {
    private ArrayList<Alarm> alarmArrayList = new ArrayList<Alarm>();
    public AlarmListViewAdapter(){ }

    @Override
    public int getCount(){
        return alarmArrayList.size();
    }

    //여기서 리스트뷰에 데이터를 넣어줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //position은 ListView의 위치
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_list, parent, false);
        }

        TextView alarmName = (TextView) convertView.findViewById(R.id.tv_alarm_name);
        TextView whatNotice = (TextView) convertView.findViewById(R.id.db_notice_alarm);

        Alarm listViewItem = alarmArrayList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        alarmName.setText(listViewItem.getAlarmName());
        whatNotice.setText(listViewItem.getWhatNotice());

        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ }
        });

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return alarmArrayList.get(position);
    }

    //데이터 값 넣어줌
    public void addAlarm(String alarmN, String whatN){
        Alarm item = new Alarm();

        item.setAlarmName(alarmN);
        item.setWhatNotice(whatN);

        alarmArrayList.add(item);
    }

}
