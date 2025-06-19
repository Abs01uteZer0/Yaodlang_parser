package com.andreypshenichnyj.iate.db.entity;

public class Family {

    private int id;

    private String code;

    public Family() {
    }

    public Family(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
