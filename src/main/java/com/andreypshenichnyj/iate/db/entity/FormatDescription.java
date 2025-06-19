package com.andreypshenichnyj.iate.db.entity;

public class FormatDescription {

    private int id;

    private int familyId;

    private int length;

    private String code;

    public FormatDescription() {
    }

    public FormatDescription(int id, int familyId, int length, String code) {
        this.id = id;
        this.familyId = familyId;
        this.length = length;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}