package com.andreypshenichnyj.iate.parser.core;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.parser.interfaces.DataParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class AbstractDataParser implements DataParser {

    protected final List<ParsedLine> parsedLines = new ArrayList<>();

    @Override
    public void saveAsJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(Paths.get(filePath).toFile(), parsedLines);
    }

    @Override
    public List<ParsedLine> getParsedLines() {
        return parsedLines;
    }

    protected void clearParsedLines() {
        parsedLines.clear();
    }

    @Override
    public void saveAsCsv(String filePath) throws IOException {
        if (parsedLines.isEmpty()) return;

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),
                java.nio.charset.StandardCharsets.UTF_8)) {

            writer.write('\uFEFF');

            Set<String> headers = parsedLines.get(0).getFieldValues().keySet();
            writer.write("lineNumber," + String.join(",", headers));
            writer.newLine();

            for (ParsedLine line : parsedLines) {
                List<String> values = new ArrayList<>();
                values.add(String.valueOf(line.getLineNumber()));
                for (String key : headers) {
                    String val = line.getFieldValues().getOrDefault(key, "");
                    values.add(escapeCsv(val));
                }
                writer.write(String.join(",", values));
                writer.newLine();
            }
        }
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    @Override
    public void saveAsTxt(String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (ParsedLine line : parsedLines) {
                writer.write("Line #" + line.getLineNumber());
                writer.newLine();
                for (Map.Entry<String, String> entry : line.getFieldValues().entrySet()) {
                    writer.write(String.format("%-20s = %s%n", entry.getKey(), entry.getValue()));
                }
                writer.write(System.lineSeparator());
            }
        }
    }

    protected String extractFieldValue(String line, int startPos, int width) {
        int endPos = Math.min(startPos + width, line.length());
        if (startPos >= line.length()) {
            return "";
        }
        return line.substring(startPos, endPos);
    }
}
