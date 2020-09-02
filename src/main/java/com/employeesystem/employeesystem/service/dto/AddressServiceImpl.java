package com.employeesystem.employeesystem.service.dto;

import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.repository.model.address.AddressRepository;
import com.employeesystem.employeesystem.service.api.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
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
    public void deleteAddress(String id) {
        Address byId = addressRepository.getOne(id);
        addressRepository.delete(byId);
    }

    @Override
    public Address getById(String id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException(id));
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
