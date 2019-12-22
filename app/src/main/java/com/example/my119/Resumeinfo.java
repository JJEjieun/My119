package com.example.my119;

public class Resumeinfo {
    String id, myFace, yourname, gender, birth, phoneNum, address, time, address1, address2, job, words;

    public Resumeinfo(String id, String myface, String yourname, String gender, String birth, String phoneNum,
                      String address, String time, String address1, String address2, String job, String words) {
        this.id = id;
        this.myFace = myface;
        this.yourname = yourname;
        this.gender = gender;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.address = address;
        this.time = time;
        this.address1 = address1;
        this.address2 = address2;
        this.job = job;
        this.words = words;
    }

    public String getId() {
        return id;
    }
    public String getMyface() {
        return myFace;
    }
    public String getYourname() {
        return yourname;
    }
    public String getGender() {
        return gender;
    }
    public String getBirth() {
        return birth;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getAddress() {
        return address;
    }
    public String getTime() {
        return time;
    }
    public String getAddress1() {
        return address1;
    }
    public String getAddress2() {
        return address2;
    }
    public String getJob() {
        return job;
    }
    public String getWords() {
        return words;
    }
}
