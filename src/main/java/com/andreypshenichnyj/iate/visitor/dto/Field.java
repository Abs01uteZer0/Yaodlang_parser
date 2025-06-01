package com.andreypshenichnyj.iate.visitor.dto;

import com.andreypshenichnyj.iate.visitor.enums.FormatType;

import java.util.EnumMap;
import java.util.Map;

public class Field {

    private String name;

    private final Map<FormatType, Format> formats = new EnumMap<>(FormatType.class);

    private String comment;

    public Field() {
    }

    public Field(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Map<FormatType, Format> getFormats() {
        return formats;
    }

    public void addFormat(Format format) {
        formats.put(format.getFormatType(), format);
    }

    public Format getBestFormat() {
        // Приоритет: FA > PC > FC > BI
        if (formats.containsKey(FormatType.FA)) return formats.get(FormatType.FA);
        if (formats.containsKey(FormatType.FC)) return formats.get(FormatType.FC);
        if (formats.containsKey(FormatType.PC)) return formats.get(FormatType.PC);
        if (formats.containsKey(FormatType.BI)) return formats.get(FormatType.BI);
        return null;
    }
}
