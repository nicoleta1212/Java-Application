package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;

import java.util.List;

public interface CarteIdentitateService {

    IDcard create(IDcardDTO carteIdentitateDTO);

    void delete(int id);

    List<IDcard> getAll();

    IDcard getById(int id);

    void update(int id, IDcardDTO carteIdentitateDTO);
    public void deleteAll();
}
