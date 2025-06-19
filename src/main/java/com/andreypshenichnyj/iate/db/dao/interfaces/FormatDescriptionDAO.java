package com.andreypshenichnyj.iate.db.dao.interfaces;

import com.andreypshenichnyj.iate.db.entity.FormatDescription;

import java.util.List;

public interface FormatDescriptionDAO {

    int insert(FormatDescription formatDescription);

    FormatDescription findById(int id);

    FormatDescription findByCode(String code);

    List<FormatDescription> findAll();

    List<FormatDescription> findByFamilyId(int familyId);
}