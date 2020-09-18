package com.employeesystem.employeesystem.service.implementation;

import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.repository.model.address.AddressRepository;
import com.employeesystem.employeesystem.service.api.AddressService;
import com.employeesystem.employeesystem.service.dto.AddressDTO;
import com.employeesystem.employeesystem.web.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;


    @Override
    public List<Address> getAll() {
      return   addressRepository.findAll();

    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setRegion(addressDTO.getRegion());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setNr(addressDTO.getNr());
        address.setBlock(addressDTO.getBlock());
        address.setStaircase(addressDTO.getStaircase());
        address.setApartment(addressDTO.getApartment());
        return addressRepository.save(address);
    }

    @Override
    public Address getById(String id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address with id "+ id ,id));
    }

    @Override
    public void deleteAddress(String id) {
        final Address idToDelete = getById(id);
        addressRepository.delete(idToDelete);
    }
    @Override
    public void update(String id, AddressDTO addressDTO) {
        Address updated = getById(id);
        updated.setRegion(addressDTO.getRegion());
        updated.setCity(addressDTO.getCity());
        updated.setStreet(addressDTO.getStreet());
        updated.setNr(addressDTO.getNr());
        updated.setBlock(addressDTO.getBlock());
        updated.setStaircase(addressDTO.getStaircase());
        updated.setApartment(addressDTO.getApartment());
        addressRepository.save(updated);
    }

    @Override
    public List<Address> search(String region) {
        return addressRepository.findByRegion(region);
    }
}
