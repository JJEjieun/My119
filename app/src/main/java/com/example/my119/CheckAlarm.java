package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class CheckAlarm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_alarm);

        Button btn_ev_ee = (Button)findViewById(R.id.btn_ev_ee);
        btn_ev_ee.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "기업평가", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Evaluation_employee.class);
                startActivity(intent);
            }
        });


    }

}