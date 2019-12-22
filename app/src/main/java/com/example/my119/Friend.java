package com.example.my119;

public class Friend {
    private String ID;
    private String name;
    private String storeName;

    public Friend(String ID, String name, String storeName) {
        this.ID = ID;
        this.name = name;
        this.storeName = storeName;
    }

    public Friend(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
