package com.example.my119;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class Resume extends AppCompatActivity {
    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    public String sid, myFaceUrl;
    Context context;
    EditText wwords;
    Spinner s_time, e_time, key1, key2, key3, key4, key5, key6;
    static String time, address1, address2, job, words;
    CheckBox food1, food2, food3, food4, food5, food6,food7, food8, food9,
            food10, food11, food12, food13, food14, food15, food16;
    String ss_time, ee_time, place1, place2, place3, place4, place5, place6, jjob;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin4, adspin5, adspin3of1, adspin3of2;

    Bitmap bitmap;
    Uri image;

    private static final String TAG = "resume";
    private static String IP_ADDRESS = "10.0.2.2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle("이력서");

        final TextView name = (TextView)findViewById(R.id.yourname);
        final TextView ggender = (TextView)findViewById(R.id.gender);
        final TextView bbirth = findViewById(R.id.birth);
        final TextView phone = (TextView)findViewById(R.id.phoneNum);
        final TextView add = (TextView)findViewById(R.id.address);

//        name.setText("..");
        name.setText(LoginEmployee.eName);
        ggender.setText(LoginEmployee.eGender);
        bbirth.setText(LoginEmployee.eBirth);
        phone.setText(LoginEmployee.ePhoneNum);
        add.setText(LoginEmployee.eAddress);


        context = this;
        sid = PreferenceUtil.getPreferences(context, "id");
        s_time = findViewById(R.id.enterKey3of1);
        e_time = findViewById(R.id.enterKey3of2);
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
        food4 = findViewById(R.id.food4);
        food5 = findViewById(R.id.food5);
        food6 = findViewById(R.id.food6);
        food7 = findViewById(R.id.food7);
        food8 = findViewById(R.id.service1);
        food9 = findViewById(R.id.service2);
        food10 = findViewById(R.id.service3);
        food11 = findViewById(R.id.service4);
        food12 = findViewById(R.id.service5);
        food13 = findViewById(R.id.service6);
        food14 = findViewById(R.id.service7);
        food15 = findViewById(R.id.service8);
        food16 = findViewById(R.id.service9);
//        ss_time = s_time.getSelectedItem().toString();
//        ee_time = e_time.getSelectedItem().toString();
//        place1 = spin1.getSelectedItem().toString();
//        place2 = spin2.getSelectedItem().toString();
//        place3 = spin3.getSelectedItem().toString();
//        place4 = spin4.getSelectedItem().toString();
//        place5 = spin5.getSelectedItem().toString();
//        place6 = spin6.getSelectedItem().toString();
//        jjob = findViewById(R.id.)
        wwords = findViewById(R.id.words);

        //내 핸드폰에서 이미지 가져오기
        imageview = (ImageView)findViewById(R.id.myFace);

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }

        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);

                myFaceUrl = String.valueOf(image);
            }
        });

        final Spinner spin3of1 = (Spinner)findViewById(R.id.enterKey3of1);
        final Spinner spin3of2 = (Spinner)findViewById(R.id.enterKey3of2);

        adspin3of1 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of1.setAdapter(adspin3of1);

        adspin3of2 = ArrayAdapter.createFromResource(this, R.array.keySpin3,
                android.R.layout.simple_spinner_dropdown_item);
        adspin3of2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3of2.setAdapter(adspin3of2);

        //희망근무지 스피너
        //스피너에 주소 입력.
        final Spinner spin1 = (Spinner)findViewById(R.id.enterAddress1);
        final Spinner spin2 = (Spinner)findViewById(R.id.enterAddress2);
        final Spinner spin4 = (Spinner)findViewById(R.id.enterAddress4);
        final Spinner spin5 = (Spinner)findViewById(R.id.enterAddress5);


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

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin2.setAdapter(null);
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

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) { }
                    });
                }else{
                    spin5.setAdapter(null);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });



        Button btnResume = findViewById(R.id.btn_r_resume);
        btnResume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = sid;
                String myFace = myFaceUrl;
                String yourname = name.getText().toString();
                String gender = ggender.getText().toString();
                String birth = bbirth.getText().toString();
                String phoneNum= phone.getText().toString();
                String address= add.getText().toString();
                String time = "";
                String address1 = "";
                String address2 = "";
                String job = "";
                String words = wwords.getText().toString();

//                yourname = name.toString();
                ss_time = s_time.getSelectedItem().toString();
                ee_time = e_time.getSelectedItem().toString();
                place1 = spin1.getSelectedItem().toString();
                place2 = spin2.getSelectedItem().toString();
//                place3 = spin3.getSelectedItem().toString();
                place4 = spin4.getSelectedItem().toString();
                place5 = spin5.getSelectedItem().toString();
//                place6 = spin6.getSelectedItem().toString();
                if (food1.isChecked()) {
                    job = "패밀리레스토랑 ";
                }
                if (food2.isChecked()) {
                    job = job + "패스트푸드점 ";
                }
                if (food3.isChecked()) {
                    job = job +"치킨전문점 ";
                }
                if (food4.isChecked()) {
                    job = job +"피자전문점 ";
                }
                if (food5.isChecked()) {
                    job = job +"커피전문점 ";
                }
                if (food6.isChecked()) {
                    job = job +"아이스크림/디저트 ";
                }
                if (food7.isChecked()) {
                    job = job +"베이커리/떡/도넛 ";
                }
                if (food8.isChecked()) {
                    job = job +"백화점 ";
                }
                if (food9.isChecked()) {
                    job = job +"쇼핑몰 ";
                }
                if (food10.isChecked()) {
                    job = job +"편의점 ";
                }
                if (food11.isChecked()) {
                    job = job +"의류/잡화 ";
                }
                if (food12.isChecked()) {
                    job = job +"서점/문구/팬시 ";
                }
                if (food13.isChecked()) {
                    job = job +"놀이공원 ";
                }
                if (food14.isChecked()) {
                    job = job +"영화/공연 ";
                }
                if (food15.isChecked()) {
                    job = job +"호텔 ";
                }
                if (food16.isChecked()) {
                    job = job +"전시/컨벤션";
                }


                time = ss_time + ee_time;
//                address1 = place1 + " " + place2 + " " + place3;
//                address2 = place4 + " " + place5 + " " + place6;
                address1 = place1 + " " + place2;
                address2 = place4 + " " + place5;
//                job = ;

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/resume.php",
                        id, myFace, yourname, gender, birth, phoneNum, address, time, address1, address2 ,job, words);

                if (words.length() > 0) {
                    wwords.getText().clear();
                }

                Toast.makeText(getApplicationContext(), "이력서가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mainPage:
                setContentView(R.layout.main_employee);
                return true;
            case R.id.resume:
                setContentView(R.layout.resume);
                return true;
            case R.id.contract:
                setContentView(R.layout.contract_employee);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }





//    //내 핸드폰에서 이미지 가져오기
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri selectedImageUri = data.getData();
//            imageview.setImageURI(selectedImageUri);
//            myFaceUrl = selectedImageUri.toString();
//        }
//    }

    //내 핸드폰에서 이미지 가져오기
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri selectedImageUri = data.getData();
//
//
//            bitmap = BitmapFactory.decodeFile(String.valueOf(selectedImageUri));
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] dataa = baos.toByteArray();
//
//            imageview.setImageURI(selectedImageUri);
//            myFaceUrl = selectedImageUri.toString();
//        }
    @Override
    public void onActivityResult(int requsetCode, int resultCode, Intent data) {
        super.onActivityResult(requsetCode, resultCode, data);

        image = data.getData();
        myFaceUrl = image.toString();
        Toast.makeText(context, myFaceUrl, Toast.LENGTH_SHORT).show();

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image);
            imageview.setImageBitmap(bitmap);
            uploadImage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void uploadImage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

    }


    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Resume.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response1 - " + result);
        }

        @Override
        protected String doInBackground(String... params) {

            String id = (String) params[1];
            String myFace = (String) params[2];
            String yourname = (String) params[3];
            String gender = (String) params[4];
            String birth = (String) params[5];
            String phoneNum = (String) params[6];
            String address = (String) params[7];
            String time = (String) params[8];
            String address1 = (String) params[9];
            String address2 = (String) params[10];
            String job = (String) params[11];
            String words = (String) params[12];


            String serverURL = (String) params[0];
            String postParameters = "id=" + id + "&myFace=" + myFace + "&yourname=" + yourname + "&gender="+gender+"&birth=" + birth
                    + "&phoneNum=" + phoneNum + "&address=" + address+"&time="+time+"&address1="+address1 +"&address2="+address2
                    +"&job="+job +"&words="+words;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code2 - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.d(TAG, "OK");
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }
        }

    }
}