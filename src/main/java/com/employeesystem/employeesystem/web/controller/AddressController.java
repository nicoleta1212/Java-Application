package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.service.api.AddressService;
import com.employeesystem.employeesystem.service.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@Valid @RequestBody AddressDTO addressDTO){
        return addressService.createAddress(addressDTO);
    }

    @GetMapping
    public List<Address>  getAll(){
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById (@NotEmpty @PathVariable String id){
        return ResponseEntity.ok(addressService.getById(id));
    }

    @DeleteMapping("/{id}")
    public  Map<String, Boolean> delete(@NotEmpty @PathVariable String id){
        addressService.deleteAddress(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Address with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Boolean> update(@NotEmpty @PathVariable String id,@Valid @RequestBody AddressDTO addressDTO){
        addressService.update(id,addressDTO);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Address with id: " + id + " has been updated:", Boolean.TRUE);

        return response;
    }

    @GetMapping("/searchByRegion")
    public ResponseEntity<List<Address>> searchByRegion(@NotEmpty @RequestParam String region){
        return ResponseEntity.of(Optional.of(addressService.search(region)));
    }
}
