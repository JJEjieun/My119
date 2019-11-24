package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Button button_resume = (Button)findViewById(R.id.manageS);
        button_resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이력서 작성 및 변경", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Resume.class);
                startActivity(intent);
            }
        });
    }

}
