package com.andreypshenichnyj.iate.db;

import com.andreypshenichnyj.iate.db.dao.impl.*;
import com.andreypshenichnyj.iate.db.dao.interfaces.*;
import com.andreypshenichnyj.iate.db.entity.*;
import com.andreypshenichnyj.iate.parser.dtos.ParsedLine;
import com.andreypshenichnyj.iate.visitor.dto.FieldDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppLoader {

    private static final String DB_URL = "jdbc:sqlite:data.db?busy_timeout=5000";

    public static void loadToDb(
            String familyCode,
            int totalLength,
            List<FieldDTO> fields,
            List<ParsedLine> datRows,
            String fileName
    ) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("PRAGMA journal_mode=WAL;");
            }

            conn.setAutoCommit(false);

            FamilyDAO familyDAO = new FamilyDAOImpl(conn);
            FormatDescriptionDAO fdDAO = new FormatDescriptionDAOImpl(conn);
            FieldDAO fieldDAO = new FieldDAOImpl(conn);
            DataValueDAO dvDAO = new DataValueDAOImpl(conn);

            int familyId;
            Family existingFamily = familyDAO.findByCode(familyCode);
            if (existingFamily == null) {
                Family family = new Family(0, familyCode);
                familyId = familyDAO.insert(family);
            } else {
                familyId = existingFamily.getId();
            }

            String formatCode = familyCode + totalLength;
            FormatDescription formatDesc = fdDAO.findByCode(formatCode);
            int formatDescId;

            if (formatDesc == null) {
                formatDesc = new FormatDescription(0, familyId, totalLength, formatCode);
                formatDescId = fdDAO.insert(formatDesc);
            } else {
                formatDescId = formatDesc.getId();
            }

            List<Field> existingFields = fieldDAO.findByFormatDescriptionId(formatDescId);
            Map<String, Field> existingFieldMap = new HashMap<>();
            for (Field f : existingFields) {
                existingFieldMap.put(f.getFieldKey(), f);
            }

            int offset = existingFields.stream()
                    .mapToInt(Field::getLength)
                    .sum();

            for (FieldDTO dto : fields) {
                Field existing = existingFieldMap.get(dto.getName());
                if (existing == null) {
                    Field field = new Field();
                    field.setFormatDescriptionId(formatDescId);
                    field.setFieldKey(dto.getName());
                    field.setLabel(dto.getName());
                    field.setAbsoluteType(dto.getFormatForParserReverse().toString());
                    field.setInStringType(dto.getFormatForParser().toString());
                    field.setDescription(dto.getComment());
                    field.setOffset(offset);

                    int length = Integer.parseInt(
                            dto.getFormatForParser().toStringWithoutPrecision().replaceAll("[A-Z(),]", "")
                    );

                    field.setLength(length);
                    offset += length;
                    fieldDAO.insert(field);
                } else if (existing.getDescription() == null && dto.getComment() != null) {
                    existing.setDescription(dto.getComment());
                    fieldDAO.update(existing);
                }
            }

            List<Field> dbFields = fieldDAO.findByFormatDescriptionId(formatDescId);
            Map<String, Integer> fieldIdMap = new HashMap<>();
            for (Field f : dbFields) {
                fieldIdMap.put(f.getLabel(), f.getId());
            }

            for (ParsedLine line : datRows) {
                for (Map.Entry<String, String> entry : line.getFieldValues().entrySet()) {
                    Integer fieldId = fieldIdMap.get(entry.getKey());
                    if (fieldId != null) {
                        dvDAO.insert(new DataValue(0, entry.getValue(), fieldId, fileName, line.getLineNumber()));
                    }
                }
            }

            conn.commit();
            System.out.println("Данные успешно сохранены в БД.");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении в БД: " + e.getMessage(), e);
        }
    }
}
