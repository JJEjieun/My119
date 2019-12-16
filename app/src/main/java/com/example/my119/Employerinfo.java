package com.example.my119;

public class Employerinfo {
    String ID;
    String PW;
    String employerNumber, compayName, name, address, phoneNum, email, sign;
    int rate;

    public Employerinfo(String id, String pw, String employerNumber, String compayName, String name,
                        String address, String phoneNum, String email, String sign, int rate) {
        this.ID = id;
        this.PW = pw;
        this.employerNumber = employerNumber;
        this.compayName = compayName;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.email = email;
        this.sign = sign;
        this.rate = rate;
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getName() {
        return name;
    }

    public String getCompayName() {
        return compayName;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployerNumber() {
        return employerNumber;
    }

    public void setCompayName(String compayName) {
        this.compayName = compayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployerNumber(String employerNumber) {
        this.employerNumber = employerNumber;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
