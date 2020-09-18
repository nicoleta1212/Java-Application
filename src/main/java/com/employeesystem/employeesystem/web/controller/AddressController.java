package com.employeesystem.employeesystem.web;

import com.employeesystem.employeesystem.repository.model.address.Address;
import com.employeesystem.employeesystem.service.api.AddressService;
import com.employeesystem.employeesystem.service.dto.AddressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@Valid @RequestBody AddressDTO addressDTO){
        return addressService.createAddress(addressDTO);
    }

    @GetMapping
    public List<Address> getAll(){
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById (@NotEmpty @PathVariable String id){
        return ResponseEntity.ok(addressService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@NotEmpty @PathVariable String id){
        addressService.deleteAddress(id);
    }

    @PutMapping("/{id}")
    public void update(@NotEmpty @PathVariable String id,@Valid @RequestBody AddressDTO addressDTO){
        addressService.update(id,addressDTO);
    }

    @GetMapping("/searchByRegion")
    public ResponseEntity<List<Address>> searchByRegion(@NotEmpty @RequestParam String region){
        return ResponseEntity.of(Optional.of(addressService.search(region)));
    }
}
