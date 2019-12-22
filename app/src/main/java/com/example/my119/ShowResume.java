package com.example.my119;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.my119.Login.applyinfos;
import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.noticeinfos;
import static com.example.my119.Login.resumeinfos;

public class ShowResume extends AppCompatActivity {
    String eid, num, name, gender, phoneNum, address, startTime,
            address1, address2, category, words, companyName, myFace, birth;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_resume);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("이력서보기");

        ImageView userFace = (ImageView)findViewById(R.id.myFace);  //신청인 이력서 사진
        TextView userName = (TextView)findViewById(R.id.yourname);  //신청인 이름
        TextView userGender = (TextView)findViewById(R.id.gender);  //신청인 성별
        TextView userBirth  = (TextView)findViewById(R.id.birth);
        TextView userPhoneNum = (TextView)findViewById(R.id.db_phoneNum);  //신청인 폰번호
        TextView userAddress = (TextView)findViewById(R.id.db_address);  //신청인 집주소
        TextView userStartTime = (TextView)findViewById(R.id.db_enterKey3of1);  //신청인 희망근무시간
        TextView userAddress1 = (TextView)findViewById(R.id.db_enterAddress1);  //신청인 희망근무지1
        TextView userAddress2 = (TextView)findViewById(R.id.db_enterAddress2);  //신청인 희망근무지2
        TextView userCategory = (TextView)findViewById(R.id.db_enterCagegory);  //신청인 희망업직종
        TextView userWords = (TextView)findViewById(R.id.words);  //신청인 하고싶은말

        //기업측의 아이디 가져오기
        for (int i = 0; i < employerinfos.size(); i++) {
            if (employerinfos.get(i).getID().equals(LoginEmployer.rID)) {
                companyName = employerinfos.get(i).getCompayName();
            }
        }

        //기업측의 회사명 가져오기
        for (int i = 0; i < noticeinfos.size(); i++) {
            if (noticeinfos.get(i).getStoreName().equals(companyName)) {
                num = noticeinfos.get(i).getNum();
            }
        }

        //공고에 근무를 신청한 개인의 아이디 가져오기
        for (int i = 0; i < applyinfos.size(); i++) {
            if (applyinfos.get(i).getNum().equals(num)) {
                eid = applyinfos.get(i).getEid();
            }
        }

        //이력서 정보에서 알바를 신청한 사람의 아이디를 통해 이력서를 가져온다
        for (int i = 0; i < resumeinfos.size(); i++) {
            if (resumeinfos.get(i).getId().equals(eid)) {
                name = resumeinfos.get(i).getYourname();
                gender = resumeinfos.get(i).getGender();
                birth = resumeinfos.get(i).getBirth();
                phoneNum = resumeinfos.get(i).getPhoneNum();
                address = resumeinfos.get(i).getAddress();
                startTime = resumeinfos.get(i).getTime().replace(" ", "~");
                address1 = resumeinfos.get(i).getAddress1();
                address2 = resumeinfos.get(i).getAddress2();
                category = resumeinfos.get(i).getJob();
                words = resumeinfos.get(i).getWords();
                myFace = resumeinfos.get(i).getMyface();
            }
        }

        userName.setText(name);
        userGender.setText(gender);
        userBirth.setText(birth);
        userPhoneNum.setText(phoneNum);
        userAddress.setText(address);
        userStartTime.setText(startTime);
        userAddress1.setText(address1);
        userAddress2.setText(address2);
        userCategory.setText(category);
        userWords.setText(words);

        //사진을 이미지뷰에 넣는다
        uri = Uri.parse(myFace);
        try {
            Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            userFace.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
