package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class AppliedNoticeList extends AppCompatActivity {

    ArrayList<String> notices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applied_notice_list);

        //리스트의 버튼 클릭하면 해당공고에 해당하는 Evaluation_employer이 뜨게 함.

    }

}