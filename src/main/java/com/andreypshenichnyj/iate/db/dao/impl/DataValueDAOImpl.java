package com.andreypshenichnyj.iate.db.dao.impl;

import com.andreypshenichnyj.iate.db.dao.interfaces.DataValueDAO;
import com.andreypshenichnyj.iate.db.entity.DataValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataValueDAOImpl implements DataValueDAO {
    private final Connection connection;

    public DataValueDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(DataValue dataValue) {
        String sql = "INSERT INTO DataValue (value, field_id, source_file, line_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, dataValue.getValue());
            stmt.setInt(2, dataValue.getFieldId());
            stmt.setString(3, dataValue.getSourceFile());
            stmt.setInt(4, dataValue.getLineNumber());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    dataValue.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<DataValue> findByFieldId(int fieldId) {
        String sql = "SELECT * FROM DataValue WHERE field_id = ?";
        List<DataValue> dataValues = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fieldId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dataValues.add(new DataValue(
                            rs.getInt("id"),
                            rs.getString("value"),
                            rs.getInt("field_id"),
                            rs.getString("source_file"),
                            rs.getInt("line_number")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataValues;
    }

    @Override
    public List<DataValue> findByFormatDescriptionId(int formatDescriptionId) {
        String sql = """
                SELECT dv.id, dv.value, dv.field_id 
                FROM DataValue dv
                JOIN Field f ON dv.field_id = f.id
                WHERE f.format_description_id = ?
                """;

        List<DataValue> dataValues = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, formatDescriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dataValues.add(new DataValue(
                            rs.getInt("id"),
                            rs.getString("value"),
                            rs.getInt("field_id"),
                            rs.getString("source_file"),
                            rs.getInt("line_number")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataValues;
    }

    @Override
    public List<String> findDistinctFileNamesByFormatId(int formatId) {
        List<String> fileNames = new ArrayList<>();
        String sql = "SELECT DISTINCT source_file FROM DataValue dv " +
                "JOIN Field f ON dv.field_id = f.id " +
                "WHERE f.format_description_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, formatId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fileNames.add(rs.getString("source_file"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    @Override
    public List<DataValue> findByFormatAndFile(int formatId, String fileName) {
        List<DataValue> values = new ArrayList<>();
        String sql = "SELECT dv.* FROM DataValue dv " +
                "JOIN Field f ON dv.field_id = f.id " +
                "WHERE f.format_description_id = ? AND dv.source_file = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, formatId);
            stmt.setString(2, fileName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    values.add(new DataValue(
                            rs.getInt("id"),
                            rs.getString("value"),
                            rs.getInt("field_id"),
                            rs.getString("source_file"),
                            rs.getInt("line_number")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
}