package com.andreypshenichnyj.iate.db.entity;

public class Field {

    private int id;

    private int formatDescriptionId;

    private String fieldKey;

    private String label;

    private String absoluteType;

    private String inStringType;

    private String description;

    private int offset;

    private int length;

    public Field() {
    }

    public Field(int id, int formatDescriptionId, String fieldKey, String label, String absoluteType, String inStringType, String description, int offset, int length) {
        this.id = id;
        this.formatDescriptionId = formatDescriptionId;
        this.fieldKey = fieldKey;
        this.label = label;
        this.absoluteType = absoluteType;
        this.inStringType = inStringType;
        this.description = description;
        this.offset = offset;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormatDescriptionId() {
        return formatDescriptionId;
    }

    public void setFormatDescriptionId(int formatDescriptionId) {
        this.formatDescriptionId = formatDescriptionId;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbsoluteType() {
        return absoluteType;
    }

    public void setAbsoluteType(String absoluteType) {
        this.absoluteType = absoluteType;
    }

    public String getInStringType() {
        return inStringType;
    }

    public void setInStringType(String inStringType) {
        this.inStringType = inStringType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}