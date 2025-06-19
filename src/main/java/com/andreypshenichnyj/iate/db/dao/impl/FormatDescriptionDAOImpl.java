package com.andreypshenichnyj.iate.db.dao.impl;

import com.andreypshenichnyj.iate.db.dao.interfaces.FormatDescriptionDAO;
import com.andreypshenichnyj.iate.db.entity.FormatDescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormatDescriptionDAOImpl implements FormatDescriptionDAO {
    private final Connection connection;

    public FormatDescriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(FormatDescription formatDescription) {
        String sql = "INSERT INTO FormatDescription (family_id, length, code) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, formatDescription.getFamilyId());
            stmt.setInt(2, formatDescription.getLength());
            stmt.setString(3, formatDescription.getCode());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    formatDescription.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public FormatDescription findById(int id) {
        String sql = "SELECT * FROM FormatDescription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FormatDescription(
                            rs.getInt("id"),
                            rs.getInt("family_id"),
                            rs.getInt("length"),
                            rs.getString("code")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FormatDescription findByCode(String code) {
        String sql = "SELECT * FROM FormatDescription WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FormatDescription(
                            rs.getInt("id"),
                            rs.getInt("family_id"),
                            rs.getInt("length"),
                            rs.getString("code")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FormatDescription> findAll() {
        List<FormatDescription> result = new ArrayList<>();
        String sql = "SELECT * FROM FormatDescription";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new FormatDescription(
                        rs.getInt("id"),
                        rs.getInt("family_id"),
                        rs.getInt("length"),
                        rs.getString("code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<FormatDescription> findByFamilyId(int familyId) {
        List<FormatDescription> formats = new ArrayList<>();
        String sql = "SELECT * FROM FormatDescription WHERE family_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, familyId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    formats.add(new FormatDescription(
                            rs.getInt("id"),
                            rs.getInt("family_id"),
                            rs.getInt("length"),
                            rs.getString("code")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formats;
    }
}