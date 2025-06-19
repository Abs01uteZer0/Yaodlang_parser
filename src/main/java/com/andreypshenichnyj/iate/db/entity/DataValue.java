package com.andreypshenichnyj.iate.db.entity;

public class DataValue {

    private int id;

    private String value;

    private int fieldId;

    private String sourceFile;

    private int lineNumber;

    public DataValue() {
    }

    public DataValue(int id, String value, int fieldId, String sourceFile, int lineNumber) {
        this.id = id;
        this.value = value;
        this.fieldId = fieldId;
        this.sourceFile = sourceFile;
        this.lineNumber = lineNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}