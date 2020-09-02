package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.carteIdentitate.CarteIdentitate;
import com.employeesystem.employeesystem.service.dto.CarteIdentitateDTO;

import java.util.List;

public interface CarteIdentitateService {

    CarteIdentitate create(CarteIdentitateDTO carteIdentitateDTO);

    void delete(int id);

    List<CarteIdentitate> getAll();

    CarteIdentitate getById(int id);

    void update(int id, CarteIdentitateDTO carteIdentitateDTO);
}
