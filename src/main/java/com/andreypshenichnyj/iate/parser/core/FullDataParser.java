package com.andreypshenichnyj.iate.parser.core;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;
import com.andreypshenichnyj.iate.visitor.dto.Format;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FullDataParser extends AbstractDataParser {

    @Override
    public void parse(List<String> lines, List<FieldDTO> fieldDTOS) {
        clearParsedLines();

        int lineNumber = 1;

        for (String line : lines) {
            int pos = 0;
            Map<String, String> values = new LinkedHashMap<>();

            for (FieldDTO fieldDTO : fieldDTOS) {
                Format format = fieldDTO.getFormatForParser();
                if (format == null || format.getWidth() <= 0) {
                    continue;
                }

                String value = extractFieldValue(line, pos, format.getWidth());
                values.put(fieldDTO.getName(), value.trim());
                pos += format.getWidth();
            }

            parsedLines.add(new ParsedLine(lineNumber++, values));
        }
    }
}
