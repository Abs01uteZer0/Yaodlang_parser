package com.andreypshenichnyj.iate.visitor.dto;

import com.andreypshenichnyj.iate.visitor.enums.FormatType;

public class Format {

    private FormatType formatType;

    private int width;

    private Integer precision;

    public Format() {
    }

    public Format(FormatType formatType, int width, Integer precision) {
        this.formatType = formatType;
        this.width = width;
        this.precision = precision;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        return precision == null ? formatType + "(" + width + ")" : formatType + "(" + width + "," + precision + ")";
    }
}
