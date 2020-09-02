package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.carteIdentitate.CarteIdentitate;
import com.employeesystem.employeesystem.repository.model.carteIdentitate.CarteIdentitateRepository;
import com.employeesystem.employeesystem.service.api.CarteIdentitateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteIdentitateServiceImpl implements CarteIdentitateService {
    private CarteIdentitateRepository carteIdentitateRepository;

    public CarteIdentitateServiceImpl(CarteIdentitateRepository carteIdentitateRepository) {
        this.carteIdentitateRepository = carteIdentitateRepository;
    }

    @Override
    public CarteIdentitate create(CarteIdentitateDTO carteIdentitateDTO) {
        CarteIdentitate carteIdentitate = new CarteIdentitate();
        carteIdentitate.setSerie(carteIdentitateDTO.getSerie());
        carteIdentitate.setNumber(carteIdentitateDTO.getNumber());
        carteIdentitate.setCNP(carteIdentitateDTO.getCNP());
        carteIdentitate.setCetatenie(carteIdentitateDTO.getCetatenie());
        carteIdentitate.setEmisDe(carteIdentitateDTO.getEmisDe());
        carteIdentitate.setDataEmitere(carteIdentitateDTO.getDataEmitere());
        carteIdentitate.setDataExpirare(carteIdentitateDTO.getDataExpirare());

        return carteIdentitateRepository.save(carteIdentitate);
    }

    @Override
    public void delete(int id) {
        final CarteIdentitate toDelete = getById(id);
        carteIdentitateRepository.delete(toDelete);
    }

    @Override
    public List<CarteIdentitate> getAll() {
        return carteIdentitateRepository.findAll();
    }

    @Override
    public CarteIdentitate getById(int id) {
         return carteIdentitateRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not existing!"));

    }

    @Override
    public void update(int id, CarteIdentitateDTO carteIdentitateDTO) {
        final CarteIdentitate updated = getById(id);
        updated.setSerie(carteIdentitateDTO.getSerie());
        updated.setNumber(carteIdentitateDTO.getNumber());
        updated.setCNP(carteIdentitateDTO.getCNP());
        updated.setCetatenie(carteIdentitateDTO.getCetatenie());
        updated.setEmisDe(carteIdentitateDTO.getEmisDe());
        updated.setDataEmitere(carteIdentitateDTO.getDataEmitere());
        updated.setDataExpirare(carteIdentitateDTO.getDataExpirare());

        carteIdentitateRepository.save(updated);
    }
}
