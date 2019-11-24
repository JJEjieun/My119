package com.example.my119;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Objects;

public class Resume extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3, adspin4, adspin5, adspin6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);

        //내 핸드폰에서 이미지 가져오기
        imageview = (ImageView)findViewById(R.id.myFace);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        //희망근무지 스피너
        //스피너에 주소 입력.
        final Spinner spin1 = (Spinner)findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterAddress2);
        final Spinner spin3 = (Spinner)findViewById(R.id.enterAddress3);
        final Spinner spin4 = (Spinner)findViewById(R.id.enterAddress4);
        final Spinner spin5 = (Spinner)findViewById(R.id.enterAddress5);
        final Spinner spin6 = (Spinner)findViewById(R.id.enterAddress6);
        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin1.getItem(i), "서울")) {
                    adspin2 = ArrayAdapter.createFromResource(Resume.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin2.getItem(j),"성북구")) {
                                adspin3 = ArrayAdapter.createFromResource(Resume.this,
                                        R.array.spinner3, android.R.layout.simple_spinner_dropdown_item);
                                adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin3.setAdapter(adspin3);
                            }else{
                                spin3.setAdapter(null);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin2.setAdapter(null);
                    spin3.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        adspin4 = ArrayAdapter.createFromResource(this, R.array.spinner1,
                android.R.layout.simple_spinner_dropdown_item);
        adspin4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin4.setAdapter(adspin1);
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Objects.equals(adspin1.getItem(i), "서울")) {
                    adspin5 = ArrayAdapter.createFromResource(Resume.this,
                            R.array.spinner2, android.R.layout.simple_spinner_dropdown_item);
                    adspin5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin5.setAdapter(adspin2);
                    spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            if (Objects.equals(adspin5.getItem(j),"성북구")) {
                                adspin6 = ArrayAdapter.createFromResource(Resume.this,
                                        R.array.spinner3, android.R.layout.simple_spinner_dropdown_item);
                                adspin6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spin6.setAdapter(adspin6);
                            }else{
                                spin6.setAdapter(null);
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin5.setAdapter(null);
                    spin6.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });



    }


    //내 핸드폰에서 이미지 가져오기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }




    }
}