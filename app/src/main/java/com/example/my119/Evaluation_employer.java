package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Evaluation_employer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_employer);

        Button button_resume = (Button)findViewById(R.id.r_ev_er);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업평가 등록", Toast.LENGTH_SHORT).show();
                //평가 디비에 저장
                finish();
            }
        });

    }
}
