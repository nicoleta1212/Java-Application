package com.employeesystem.employeesystem.service.api;

import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.service.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<Address> getAll();

    Address createAddress(AddressDTO addressDTO);

    void deleteAddress(String id);

    Address getById(String id);

    void update(String id, AddressDTO addressDTO);

    List<Address> search(String name);
}
