package com.andreypshenichnyj.iate.db.dao.impl;

import com.andreypshenichnyj.iate.db.dao.interfaces.FieldDAO;
import com.andreypshenichnyj.iate.db.entity.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FieldDAOImpl implements FieldDAO {
    private final Connection connection;

    public FieldDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Field field) {
        String sql = "INSERT INTO Field (format_description_id, field_key, label, absolute_type, in_string_type, description, length, offset) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, field.getFormatDescriptionId());
            stmt.setString(2, field.getFieldKey());
            stmt.setString(3, field.getLabel());
            stmt.setString(4, field.getAbsoluteType());
            stmt.setString(5, field.getInStringType());
            stmt.setString(6, field.getDescription());
            stmt.setInt(7, field.getLength());
            stmt.setInt(8, field.getOffset());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    field.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Field> findByFormatDescriptionId(int formatDescriptionId) {
        String sql = "SELECT * FROM Field WHERE format_description_id = ?";
        List<Field> fields = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, formatDescriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    fields.add(new Field(
                            rs.getInt("id"),
                            rs.getInt("format_description_id"),
                            rs.getString("field_key"),
                            rs.getString("label"),
                            rs.getString("absolute_type"),
                            rs.getString("in_string_type"),
                            rs.getString("description"),
                            rs.getInt("offset"),
                            rs.getInt("length")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }

    @Override
    public Field findById(int id) {
        String sql = "SELECT * FROM Field WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Field(
                            rs.getInt("id"),
                            rs.getInt("format_description_id"),
                            rs.getString("field_key"),
                            rs.getString("label"),
                            rs.getString("absolute_type"),
                            rs.getString("in_string_type"),
                            rs.getString("description"),
                            rs.getInt("offset"),
                            rs.getInt("length")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Field field) {
        String sql = "UPDATE Field SET field_key = ?, label = ?, absolute_type = ?, in_string_type = ?, description = ?, offset = ?, length = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, field.getFieldKey());
            stmt.setString(2, field.getLabel());
            stmt.setString(3, field.getAbsoluteType());
            stmt.setString(4, field.getInStringType());
            stmt.setString(5, field.getDescription());
            stmt.setInt(6, field.getOffset());
            stmt.setInt(7, field.getLength());
            stmt.setInt(8, field.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}