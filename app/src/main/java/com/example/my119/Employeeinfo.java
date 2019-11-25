package com.example.my119;

public class Employeeinfo {
    String ID;
    String PW;

    public Employeeinfo(String id, String pw){
        this.ID = id;
        this.PW = pw;
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }
}
