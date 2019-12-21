package com.example.my119;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.my119.Login.applyinfos;
import static com.example.my119.Login.employeeinfos;
import static com.example.my119.Login.employerinfos;
import static com.example.my119.Login.noticeinfos;
import static com.example.my119.PreferenceUtil.getPreferences;

public class Contract_employee extends AppCompatActivity {
    private PaintView paintView;
    ConstraintLayout layout;
    Button button_clear, toPdf;
    TextView rName, eName, dates, eNa, eaddress, ephoneNum, workPlace, time, money, giveMethod, giveM, getToday
            , companyName, rNa, workingPlace, rphoneNum;
    ImageView employee_sign, employer_sign;
    String eeName, rrName, ddate, eeAddress, eephoneNum, num, wworkPlace, ttime, mmoney, ggiveMethod
            , ccompanyName, wworkingPlace, rrphoneNum;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_employee);

        context = this;
        rName = (TextView) findViewById(R.id.rName);
        eName = (TextView) findViewById(R.id.eName);
        eNa = findViewById(R.id.eNa);
        dates = findViewById(R.id.dates);
        eaddress = findViewById(R.id.eaddress);
        ephoneNum = findViewById(R.id.ephoneNum);
        workPlace = findViewById(R.id.workPlace);
        time = findViewById(R.id.time);
        money = findViewById(R.id.money);
        giveM = findViewById(R.id.giveM);
        giveMethod = findViewById(R.id.giveMethod);
        getToday = findViewById(R.id.getToday);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy년 MM월dd일");
        Date time1 = new Date();
        String time2 = format2.format(time1);
        companyName = findViewById(R.id.companyName);
        rNa = findViewById(R.id.rNa);
        workingPlace = findViewById(R.id.workingPlace);
        rphoneNum = findViewById(R.id.rphoneNum);

        layout = (ConstraintLayout) findViewById(R.id.rootLayout);

        toPdf = (Button) findViewById(R.id.finish_write);

        employee_sign = findViewById(R.id.employee_sign);
        employer_sign = findViewById(R.id.employer_sign);




        for (int i = 0; i < employeeinfos.size(); i++) {
            if (employeeinfos.get(i).getID().equals(LoginEmployee.eID)) {
                eeName = employeeinfos.get(i).getName();
                eephoneNum = employeeinfos.get(i).getPhoneNum();
                eeAddress = employeeinfos.get(i).getAddress();
            }
        }

        eName.setText(eeName);
        eNa.setText(eeName);
        eaddress.setText(eeAddress);
        ephoneNum.setText(eephoneNum);

        for (int i = 0; i < applyinfos.size(); i++) {
            if (applyinfos.get(i).getEid().equals(LoginEmployee.eID)) {
                num = applyinfos.get(i).getNum();

            }
        }

        for (int i = 0; i < noticeinfos.size(); i++) {
            if (num.equals(noticeinfos.get(i).getNum())) {
                ddate = noticeinfos.get(i).getDate();
                wworkPlace = noticeinfos.get(i).getKey2();
                ttime = noticeinfos.get(i).getKey3().replace(" ", "~");
                mmoney = noticeinfos.get(i).getPay();
                ggiveMethod = noticeinfos.get(i).getPaymethod();
                ccompanyName = noticeinfos.get(i).getStoreName();
            }
        }

        workPlace.setText(wworkPlace);
        dates.setText(ddate);
        companyName.setText(ccompanyName);
        time.setText(ttime);
        money.setText(mmoney);
        giveM.setText(ddate);
        giveMethod.setText(ggiveMethod);
        getToday.setText(time2);


        for (int i = 0; i < employerinfos.size(); i++) {
            if (employerinfos.get(i).getCompayName().equals(ccompanyName)) {
                rrName = employerinfos.get(i).getName();
                wworkingPlace = employerinfos.get(i).getAddress();
                rrphoneNum = employerinfos.get(i).getPhoneNum();
            }
        }
        rName.setText(rrName);
        rNa.setText(rrName);
        workingPlace.setText(wworkPlace);
        rphoneNum.setText(rrphoneNum);

        Button toPdf = (Button) findViewById(R.id.finish_write);
        toPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutToPdf(layout);
            }
        });

        String pathE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/screenshotE.png";
        employee_sign.setImageURI(Uri.parse(pathE));
        String pathR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/screenshotR.png";
        employer_sign.setImageURI(Uri.parse(pathR));

    }

    // 화면 상의 근로계약서를 PDF 파일로 저장
    public void layoutToPdf(View view) {
        Bitmap bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());

            Document document = new Document();
            String dirpath = Environment.getExternalStorageDirectory().toString();

            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/contract.pdf"));
            document.open();

            Image image = Image.getInstance(f.toString());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100;
            image.scalePercent(scaler);
            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(image);
            document.close();
            Toast.makeText(this, "PDF 파일 저장성공", Toast.LENGTH_SHORT).show();

            f.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

