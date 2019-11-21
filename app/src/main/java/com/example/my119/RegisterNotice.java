package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterNotice extends AppCompatActivity {

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3of1, adspin3of2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_notice);

        //공고 등록 버튼 누르면 공고 등록 됨
/**/    //데베 작업 필요
        Button btnNewNotice = (Button)findViewById(R.id.btn_register);
        btnNewNotice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "공고가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //스피너처리
        final Spinner spin1 = (Spinner)findViewById(R.id.enterKey1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterKey2);
        final Spinner spin3of1 = (Spinner)findViewById(R.id.enterKey3of1);
        final Spinner spin3of2 = (Spinner)findViewById(R.id.enterKey3of2);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.keySpin1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin2 = ArrayAdapter.createFromResource(this, R.array.keySpin2,
                android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin3of1 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of1.setAdapter(adspin3of1);
        spin3of1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        adspin3of2 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of2.setAdapter(adspin3of2);
        spin3of2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //아이템클릭했을때 이벤트 처리(디비에 저장)
            /**/            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

    }
}
