package com.andreypshenichnyj.iate.parser.dtos;

import java.util.Map;

public class ParsedLine {

    private final int lineNumber;

    private final Map<String, String> fieldValues;

    public ParsedLine(int lineNumber, Map<String, String> fieldValues) {
        this.lineNumber = lineNumber;
        this.fieldValues = fieldValues;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Map<String, String> getFieldValues() {
        return fieldValues;
    }
}