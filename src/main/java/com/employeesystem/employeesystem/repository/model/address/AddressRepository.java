package com.employeesystem.employeesystem.repository.model.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,String> {
    List<Address> findByRegion(String name);
}
