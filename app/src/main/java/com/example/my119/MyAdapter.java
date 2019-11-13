package com.example.my119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(this,linearLayoutManager.getOrientation()));
        recyclerView.setLayoutManager(linearLayoutManager);

        // ArrayList에 UserInfo 객체 넣기
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(new UserInfo("aaa","pwpw","홍길동",980101,"여","01012345678", new String[]{"서울특별시", "강동구"}));

        // Adapter생성
        recyclerViewAdapter = new RecyclerViewAdapter(this,userInfoList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}