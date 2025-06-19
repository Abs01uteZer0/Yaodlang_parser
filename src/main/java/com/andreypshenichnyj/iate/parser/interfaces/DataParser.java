package com.andreypshenichnyj.iate.parser.interfaces;

import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;

import java.io.IOException;
import java.util.List;

public interface DataParser {
    void parse(List<String> lines, List<FieldDTO> fieldDTOS);

    void saveAsJson(String filePath) throws IOException;

    void saveAsCsv(String filePath) throws IOException;

    void saveAsTxt(String filePath) throws IOException;

    List<ParsedLine> getParsedLines();

}