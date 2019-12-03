package com.example.my119;

public class Noticeinfo {
    String storeName, pay, date, endtime, key1, key2, key3, paymethod,interview;

    public Noticeinfo(String storeName, String pay, String date, String endtime, String key1, String key2, String key3, String paymethod, String interview){
        this.storeName = storeName;
        this.pay = pay;
        this.date = date;
        this.endtime = endtime;
        this.key1 = key1;
        this.key2= key2;
        this.key3 = key3;
        this.paymethod = paymethod;
        this.interview = interview;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getPay() {
        return pay;
    }

    public String getDate() {
        return date;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getKey3() {
        return key3;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public String getInterview() {
        return interview;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }
}
