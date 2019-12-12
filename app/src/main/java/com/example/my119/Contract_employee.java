package com.example.my119;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Contract_employee extends AppCompatActivity {
    private PaintView paintView;
    ConstraintLayout layout;
    Button button_clear, toPdf;
    TextView eName, eAdd, ePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_employee);

//        eName= (TextView)findViewById(R.id.eName);
//        eAdd = (TextView)findViewById(R.id.eAdd);
//        ePhone = (TextView)findViewById(R.id.ePhone);
        layout = (ConstraintLayout) findViewById(R.id.rootLayout);
//        button_clear = (Button)findViewById(R.id.clear);
        toPdf = (Button) findViewById(R.id.finish_write);

//        eName.setText(LoginEmployee.eName);
//        eAdd.setText(LoginEmployee.eAddress);
//        ePhone.setText(LoginEmployee.ePhoneNum);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);



//        button_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                paintView.clear();
//            }
//        });

        Button toPdf = (Button) findViewById(R.id.finish_write);
        toPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutToPdf(layout);
            }
        });
    }

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
