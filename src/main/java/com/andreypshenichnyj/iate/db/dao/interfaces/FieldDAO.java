package com.andreypshenichnyj.iate.db.dao.interfaces;

import com.andreypshenichnyj.iate.db.entity.Field;

import java.util.List;

public interface FieldDAO {

    int insert(Field field);

    List<Field> findByFormatDescriptionId(int formatDescriptionId);

    Field findById(int id);

    void update(Field field);
}