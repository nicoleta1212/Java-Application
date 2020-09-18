package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.repository.model.IDcard.IDcardRepository;
import com.employeesystem.employeesystem.service.api.IDcardService;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDcardServiceImpl implements IDcardService {
    @Autowired
    private IDcardRepository iDcardRepository;


    @Override
    public IDcard create(IDcardDTO iDcardDTO) {
        IDcard iDcard = new IDcard();
        iDcard.setSeries(iDcardDTO.getSeries());
        iDcard.setNumber(iDcardDTO.getNumber());
        iDcard.setCnp(iDcardDTO.getCnp());
        iDcard.setCitizenship(iDcardDTO.getCitizenship());
        iDcard.setIssuedBy(iDcardDTO.getIssuedBy());
        iDcard.setDateOfIssue(iDcardDTO.getDateOfIssue());
        iDcard.setExpirationDate(iDcardDTO.getExpirationDate());

        return iDcardRepository.save(iDcard);
    }

    @Override
    public void delete(String id) {
        final IDcard toDelete = getById(id);
        iDcardRepository.delete(toDelete);
    }

    @Override
    public List<IDcard> getAll() {
        return iDcardRepository.findAll();
    }

    @Override
    public IDcard getById(String id) {
         return iDcardRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Id card "));

    }

    @Override
    public void update(String id, IDcardDTO iDcardDTO) {
        final IDcard updated = getById(id);
        updated.setSeries(iDcardDTO.getSeries());
        updated.setNumber(iDcardDTO.getNumber());
        updated.setCnp(iDcardDTO.getCnp());
        updated.setCitizenship(iDcardDTO.getCitizenship());
        updated.setIssuedBy(iDcardDTO.getIssuedBy());
        updated.setDateOfIssue(iDcardDTO.getDateOfIssue());
        updated.setExpirationDate(iDcardDTO.getExpirationDate());

        iDcardRepository.save(updated);
    }

    public void deleteAll(){
        iDcardRepository.deleteAll();
    }
}
