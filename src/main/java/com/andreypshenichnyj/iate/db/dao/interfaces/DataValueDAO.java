package com.andreypshenichnyj.iate.db.dao.interfaces;

import com.andreypshenichnyj.iate.db.entity.DataValue;

import java.util.List;

public interface DataValueDAO {

    int insert(DataValue dataValue);

    List<DataValue> findByFieldId(int fieldId);

    List<DataValue> findByFormatDescriptionId(int formatDescriptionId);

    List<String> findDistinctFileNamesByFormatId(int formatId);

    List<DataValue> findByFormatAndFile(int formatId, String fileName);
}
