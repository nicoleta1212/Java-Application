package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.repository.model.IDcard.IDcardRepository;
import com.employeesystem.employeesystem.service.api.CarteIdentitateService;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteIdentitateServiceImpl implements CarteIdentitateService {
    private IDcardRepository carteIdentitateRepository;

    public CarteIdentitateServiceImpl(IDcardRepository carteIdentitateRepository) {
        this.carteIdentitateRepository = carteIdentitateRepository;
    }

    @Override
    public IDcard create(IDcardDTO carteIdentitateDTO) {
        IDcard carteIdentitate = new IDcard();
        carteIdentitate.setSeries(carteIdentitateDTO.getSerie());
        carteIdentitate.setNumber(carteIdentitateDTO.getNumber());
        carteIdentitate.setCNP(carteIdentitateDTO.getCNP());
        carteIdentitate.setCitizenship(carteIdentitateDTO.getCetatenie());
        carteIdentitate.setIssuedBy(carteIdentitateDTO.getEmisDe());
        carteIdentitate.setDateOfIssue(carteIdentitateDTO.getDateOfIssue());
        carteIdentitate.setExpirationDate(carteIdentitateDTO.getExpirationDate());

        return carteIdentitateRepository.save(carteIdentitate);
    }

    @Override
    public void delete(int id) {
        final IDcard toDelete = getById(id);
        carteIdentitateRepository.delete(toDelete);
    }

    @Override
    public List<IDcard> getAll() {
        return carteIdentitateRepository.findAll();
    }

    @Override
    public IDcard getById(int id) {
         return carteIdentitateRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not existing!"));

    }

    @Override
    public void update(int id, IDcardDTO carteIdentitateDTO) {
        final IDcard updated = getById(id);
        updated.setSeries(carteIdentitateDTO.getSerie());
        updated.setNumber(carteIdentitateDTO.getNumber());
        updated.setCNP(carteIdentitateDTO.getCNP());
        updated.setCitizenship(carteIdentitateDTO.getCetatenie());
        updated.setIssuedBy(carteIdentitateDTO.getEmisDe());
        updated.setDateOfIssue(carteIdentitateDTO.getDateOfIssue());
        updated.setExpirationDate(carteIdentitateDTO.getExpirationDate());

        carteIdentitateRepository.save(updated);
    }

    public void deleteAll(){
        carteIdentitateRepository.deleteAll();
    }
}
