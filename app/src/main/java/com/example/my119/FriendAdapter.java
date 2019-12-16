package com.example.my119;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class FriendAdapter extends BaseAdapter {
    private ArrayList<Friend> FriendArrayList = new ArrayList<Friend>();
    public FriendAdapter(){ }

    @Override
    public int getCount(){
        return FriendArrayList.size();
    }

    //여기서 리스트뷰에 데이터를 넣어줌
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.freind_list, parent, false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.friend_id);
        TextView name = (TextView) convertView.findViewById(R.id.friend_name);
        TextView storeName = (TextView) convertView.findViewById(R.id.friend_store_name);

        Friend listViewItem = FriendArrayList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        id.setText(listViewItem.getID());
        name.setText(listViewItem.getName());
        storeName.setText(listViewItem.getStoreName());

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return FriendArrayList.get(position);
    }

    //데이터 값 넣어줌
    public void addFriends(String id, String name, String storeName){
        Friend item = new Friend(id,name,storeName);

        item.setID(id);
        item.setName(name);
        item.setStoreName(storeName);

        FriendArrayList.add(item);
    }

    public String getID(int position){
        return FriendArrayList.get(position).getID();
    }

    public String getName(int position){
        return FriendArrayList.get(position).getName();
    }

    public String getStoreName(int position){
        return FriendArrayList.get(position).getStoreName();
    }

}

