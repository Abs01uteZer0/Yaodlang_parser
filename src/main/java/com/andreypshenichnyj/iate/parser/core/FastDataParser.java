package com.andreypshenichnyj.iate.parser.core;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;
import com.andreypshenichnyj.iate.visitor.dto.Format;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FastDataParser extends AbstractDataParser {

    @Override
    public void parse(List<String> lines, List<FieldDTO> fieldDTOS) {
        AtomicInteger counter = new AtomicInteger(1);

        IntStream.range(0, lines.size())
                .parallel()
                .forEach(i -> {
                    String line = lines.get(i);
                    int lineNumber = counter.getAndIncrement();
                    parseLine(line, lineNumber, fieldDTOS);
                });
    }

    private void parseLine(String line, int lineNumber, List<FieldDTO> fieldDTOS) {
        int pos = 0;
        Map<String, String> values = new LinkedHashMap<>();

        for (FieldDTO fieldDTO : fieldDTOS) {
            Format bestFormat = fieldDTO.getFormatForParser();
            if (bestFormat == null || bestFormat.getWidth() <= 0) continue;

            String value = extractFieldValue(line, pos, bestFormat.getWidth());
            values.put(fieldDTO.getName(), value.trim());
            pos += bestFormat.getWidth();
        }

        parsedLines.add(new ParsedLine(lineNumber, values));
    }
}
