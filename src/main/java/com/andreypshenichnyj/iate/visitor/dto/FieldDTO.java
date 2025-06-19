package com.andreypshenichnyj.iate.visitor.dto;

import com.andreypshenichnyj.iate.visitor.enums.FormatType;

import java.util.EnumMap;
import java.util.Map;

public class FieldDTO {

    private String name;

    private final Map<FormatType, Format> formats = new EnumMap<>(FormatType.class);

    private String comment;

    public FieldDTO() {
    }

    public FieldDTO(String name, String comment) {
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

    public Format getFormatForParser() {
        // Приоритет: FA > FC > PC > BI
        if (formats.containsKey(FormatType.FA)) return formats.get(FormatType.FA);
        if (formats.containsKey(FormatType.FC)) return formats.get(FormatType.FC);
        if (formats.containsKey(FormatType.PC)) return formats.get(FormatType.PC);
        if (formats.containsKey(FormatType.BI)) return formats.get(FormatType.BI);
        return null;
    }

    public Format getFormatForParserReverse() {
        // Приоритет: BI > PC > FC > FA
        if (formats.containsKey(FormatType.BI)) return formats.get(FormatType.BI);
        if (formats.containsKey(FormatType.PC)) return formats.get(FormatType.PC);
        if (formats.containsKey(FormatType.FC)) return formats.get(FormatType.FC);
        if (formats.containsKey(FormatType.FA)) return formats.get(FormatType.FA);
        return null;
    }
}
