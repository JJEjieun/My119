package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyPageEmployer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_employer);

        Button btnResume = (Button)findViewById(R.id.resume);
        btnResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Resume.class);
                startActivity(intent);
            }
        });

    }
}
