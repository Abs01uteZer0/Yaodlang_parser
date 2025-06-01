package com.andreypshenichnyj.iate.visitor.enums;

public enum FormatType {
    FA,  // Fixed Alphanumeric
    PC,  // Packed Compressed
    FC,  // Floating Compressed
    BI,  // Binary Integer
    UNKNOWN;

    public static FormatType fromString(String text) {
        if (text == null) {
            return UNKNOWN;
        }
        switch (text.trim().toUpperCase()) {
            case "FA":
                return FA;
            case "FC":
                return FC;
            case "BI":
                return BI;
            case "PC":
                return PC;
            default:
                return UNKNOWN;
        }
    }
}
