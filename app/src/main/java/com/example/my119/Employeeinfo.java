package com.example.my119;

public class Employeeinfo {
    String ID;
    String PW;
    String name;
    String gender;
    String birth;
    String phoneNum;
    String address;
    String sign;
    String rate;

    public Employeeinfo(String id, String pw, String name, String gender, String birth, String phoneNum, String address,String sign,String rate) {
        this.ID = id;
        this.PW = pw;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phoneNum = phoneNum;
        this.address = address;
        this.sign = sign;
        this.rate = rate;
    }


    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

    public String getName() {
        return name;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}


