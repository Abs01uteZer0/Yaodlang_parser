package com.andreypshenichnyj.iate.db;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SchemaInitializer {

    private static final String DB_PATH = "data.db";
    private static final String SCHEMA_RESOURCE = "schema.sql";
    private static final String SCHEMA_FALLBACK_PATH = "src/main/resources/schema.sql";

    public static void initialize() {
        File dbFile = new File(DB_PATH);
        try (Connection conn = SQLiteConnector.getConnection()) {

            String sql = loadSchema();
            if (sql == null || sql.isBlank()) {
                throw new RuntimeException("Не удалось загрузить schema.sql.");
            }

            Statement stmt = conn.createStatement();
            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    stmt.executeUpdate(statement.trim() + ";");
                }
            }
            System.out.println("База данных создана и инициализирована.");

            System.out.println("Таблицы в БД:");
            Statement checkStmt = conn.createStatement();
            ResultSet rs = checkStmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
            while (rs.next()) {
                System.out.println(" - " + rs.getString("name"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации схемы БД", e);
        }
    }

    private static String loadSchema() throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(SCHEMA_RESOURCE);
        if (in != null) {
            System.out.println("Схема загружена из ресурсов (classpath).");
            return new String(in.readAllBytes());
        }

        Path fallbackPath = Paths.get(SCHEMA_FALLBACK_PATH);
        if (Files.exists(fallbackPath)) {
            System.out.println("Схема не найдена в ресурсах, загружаем с диска.");
            return Files.readString(fallbackPath);
        }

        System.out.println("Схема не найдена ни в ресурсах, ни по пути " + SCHEMA_FALLBACK_PATH);
        return null;
    }
}