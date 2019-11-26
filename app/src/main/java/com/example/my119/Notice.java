package com.example.my119;

public class Notice {

    private String workDate;
    private String workTime;
    private String Store;
    private String noticeNum;
    private String endTime;
    private String money;

    public Notice() { }

    public String getWorkDate(){
        return workDate;
    }

    public void setWorkDate(String workDate){
        this.workDate = workDate;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime){
        this.workTime = workTime;
    }

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }


    public String getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(String noticeNum) {
        this.noticeNum = noticeNum;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
