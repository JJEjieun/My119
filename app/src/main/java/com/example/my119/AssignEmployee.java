package com.example.my119;

import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AssignEmployee extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    private FirebaseAuth fbAuth;
    private EditText enterAutNumber;
    private Button checkPhoneNumber;
    private Button checkAutNumber;
    private String phoneNumber;
    private String codeSent;
    private EditText enterPhoneNumber;
    private String phoneVerificationId;
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_employee);

        fbAuth = FirebaseAuth.getInstance();

//        처리 필요.
//        1) 모든항목입력해야 인증번호 받을 수 있게
//        2) 아이디 중복확인
//        3) 전화번호 형식에 맞게 입력했는지
//        4) 생년월일 맞게 입력했는지
//        5) 인증번호 맞아야 등록처리되게

        enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
//        phoneNumber = enterPhoneNumber.getText().toString();
        enterAutNumber = findViewById(R.id.enterAutNumber);
        checkPhoneNumber = findViewById(R.id.checkPhoneNumber);
        checkAutNumber = findViewById(R.id.checkAutNumber);

        final TextView textAut = (TextView)findViewById(R.id.textAut);

        checkPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAut.setVisibility(View.VISIBLE);
                enterAutNumber.setVisibility(View.VISIBLE);
                checkAutNumber.setVisibility(View.VISIBLE);
                sendVerificationCode();
            }
        });

        checkAutNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode();
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

    public void sendVerificationCode() {
        String phoneNumber = enterPhoneNumber.getText().toString();

        if (phoneNumber.isEmpty()) {
            enterAutNumber.setError("enter your phone number");
            enterAutNumber.requestFocus();
            return;
        }


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
        }
    };

    public void verifySignInCode() {
        String code = enterAutNumber.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithCredential:success");
                    Toast.makeText(AssignEmployee.this, "Successful", Toast.LENGTH_SHORT).show();
//                    FirebaseUser user = task.getResult().getUser();
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(AssignEmployee.this, "wrong verification code", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}