package com.andreypshenichnyj.iate.parser.core;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.Field;
import com.andreypshenichnyj.iate.visitor.dto.Format;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class FastDataParser extends AbstractDataParser {

    @Override
    public void parse(List<String> lines, List<Field> fields) {
        AtomicInteger counter = new AtomicInteger(1);

        IntStream.range(0, lines.size())
                .parallel()
                .forEach(i -> {
                    String line = lines.get(i);
                    int lineNumber = counter.getAndIncrement();
                    parseLine(line, lineNumber, fields);
                });
    }

    private void parseLine(String line, int lineNumber, List<Field> fields) {
        int pos = 0;
        Map<String, String> values = new LinkedHashMap<>();

        for (Field field : fields) {
            Format bestFormat = field.getBestFormat();
            if (bestFormat == null || bestFormat.getWidth() <= 0) continue;

            String value = extractFieldValue(line, pos, bestFormat.getWidth());
            values.put(field.getName(), value.trim());
            pos += bestFormat.getWidth();
        }

        parsedLines.add(new ParsedLine(lineNumber, values));
    }
}
