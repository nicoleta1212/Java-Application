package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;

import java.util.List;

public interface IDcardService {

    IDcard create(IDcardDTO iDcardDTO);

    void delete(String id);

    List<IDcard> getAll();

    IDcard getById(String id);

    void update(String id, IDcardDTO iDcardDTO);
     void deleteAll();
}
