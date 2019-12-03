package com.example.my119;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contract_employee extends AppCompatActivity {
    private PaintView paintView;

    TextView eName, eAdd, ePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_employee);

        eName= (TextView)findViewById(R.id.eName);
        eAdd = (TextView)findViewById(R.id.eAdd);
        ePhone = (TextView)findViewById(R.id.ePhone);

        eName.setText(LoginEmployee.eName);
        eAdd.setText(LoginEmployee.eAddress);
        ePhone.setText(LoginEmployee.ePhoneNum);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        Button button_clear = (Button)findViewById(R.id.clear);
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });
    }
}
