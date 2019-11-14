package com.example.my119;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AssignEmployee extends AppCompatActivity {

    private String verificationId;
    private FirebaseAuth mAuth;
    EditText enterAutNumber;
    Button checkPhoneNumber;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employee);

        mAuth = FirebaseAuth.getInstance();

//        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 생년월일 맞게 입력했는지
//        5) 인증번호 맞아야 등록처리되게

        EditText enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
        phoneNumber = "+82" + enterPhoneNumber.getText().toString();
        enterAutNumber = findViewById(R.id.enterAutNumber);
        checkPhoneNumber = findViewById(R.id.checkPhoneNumber);

        final TextView textAut = (TextView)findViewById(R.id.textAut);
        final EditText EnterAutNum = (EditText)findViewById(R.id.enterAutNumber);
        final Button butAutNum = (Button)findViewById(R.id.checkAutNumber);

        checkPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAut.setVisibility(View.VISIBLE);
                EnterAutNum.setVisibility(View.VISIBLE);
                butAutNum.setVisibility(View.VISIBLE);
                sendVerificationCode(phoneNumber);

            }
        });


        findViewById(R.id.checkAutNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = enterAutNumber.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {
                    enterAutNumber.setError("인증번호를 입력하세요");
                    enterAutNumber.requestFocus();
                    return;
                }

                verifyCode(code);
            }
        });




        // 위의 항목들이 모두 정상적으로 처리 되었으면
        // '등록' 버튼 누르면 로그인창으로 돌아감
        if(true) {
            Button assignButton = (Button) findViewById(R.id.assignButton);
            assignButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AssignEmployee.this, "인증되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AssignEmployee.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this,               // Activity (for callback binding)
                mCallbacks
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                enterAutNumber.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(AssignEmployee.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

}