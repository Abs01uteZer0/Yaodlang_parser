package com.andreypshenichnyj.iate.db.dao.impl;

import com.andreypshenichnyj.iate.db.dao.interfaces.FamilyDAO;
import com.andreypshenichnyj.iate.db.entity.Family;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAOImpl implements FamilyDAO {

    private final Connection connection;

    public FamilyDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int insert(Family family) {
        String sql = "INSERT INTO family (code) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, family.getCode());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert Family", e);
        }
    }

    @Override
    public Family findById(int id) {
        String sql = "SELECT * FROM family WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Family(rs.getInt("id"), rs.getString("code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find Family by ID", e);
        }
        return null;
    }

    @Override
    public Family findByCode(String code) {
        String sql = "SELECT * FROM family WHERE code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Family(rs.getInt("id"), rs.getString("code"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch all families");
        }
        return null;
    }

    @Override
    public List<Family> findAll() {
        List<Family> result = new ArrayList<>();
        String sql = "SELECT * FROM family";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new Family(rs.getInt("id"), rs.getString("code")));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch all families");
        }
        return result;
    }

    @Override
    public int insertIfNotExists(String code) {
        Family existing = findByCode(code);
        if (existing != null) {
            return existing.getId();
        }

        insert(new Family(0, code));
        existing = findByCode(code);

        if (existing == null) {
            throw new RuntimeException("Failed to insert and retrieve Family with code: " + code);
        }

        return existing.getId();
    }
}