package com.example.my119;

public class Applyinfo {
    String num;
    String eid;
    String fianl; //1=대기 , 2=수락 , 3=거절
    String store;

    public Applyinfo(String num, String eid, String fianl,String store) {
        this.num = num;
        this.eid = eid;
        this.fianl = fianl;
        this.store=store;
    }


    public Applyinfo(String num, String eid) {
        this.num = num;
        this.eid = eid;
    }

    public String getNum() {
        return num;
    }

    public String getEid() {
        return eid;
    }

    public String getFianl() {
        return fianl;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setFianl(String fianl) {
        this.fianl = fianl;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
