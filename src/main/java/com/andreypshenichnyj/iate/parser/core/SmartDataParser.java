package com.andreypshenichnyj.iate.parser.core;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.Field;
import com.andreypshenichnyj.iate.visitor.dto.Format;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SmartDataParser extends AbstractDataParser {

    @Override
    public void parse(List<String> lines, List<Field> fields) {
        AtomicInteger counter = new AtomicInteger(1);

        IntStream.range(0, lines.size())
                .forEach(i -> {
                    String line = lines.get(i).trim();
                    if (line.isEmpty()) return;

                    int lineNumber = counter.getAndIncrement();
                    Map<String, String> parsedFields = parseLine(line, fields);
                    parsedLines.add(new ParsedLine(lineNumber, parsedFields));
                });
    }

    private Map<String, String> parseLine(String line, List<Field> fields) {
        Map<String, String> result = new LinkedHashMap<>();
        int pos = 0;

        for (Field field : fields) {
            Format bestFormat = field.getBestFormat();
            if (bestFormat == null || bestFormat.getWidth() <= 0) continue;

            String raw = extractFieldValue(line, pos, bestFormat.getWidth());
            String cleaned = raw.trim();

            if (!cleaned.isEmpty()) {
                result.put(field.getName(), cleaned);
            }

            pos += bestFormat.getWidth();
        }

        return result;
    }
}
