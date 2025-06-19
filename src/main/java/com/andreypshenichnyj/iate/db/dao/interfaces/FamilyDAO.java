package com.andreypshenichnyj.iate.db.dao.interfaces;

import com.andreypshenichnyj.iate.db.entity.Family;

import java.util.List;

public interface FamilyDAO {

    int insert(Family family);

    Family findById(int id);

    Family findByCode(String code);

    List<Family> findAll();

    int insertIfNotExists(String code);
}