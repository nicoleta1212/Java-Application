package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.service.api.IDcardService;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/carteIdentitate")
public class CarteController {
    private IDcardService carteIdentitateService;

    public CarteController(IDcardService carteIdentitateService) {
        this.carteIdentitateService = carteIdentitateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IDcard create(@Valid @RequestBody IDcardDTO carteIdentitateDTO){
        return carteIdentitateService.create(carteIdentitateDTO);
    }

    @GetMapping
    public List<IDcard> getAll(){
        return carteIdentitateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IDcard> getById(@NotEmpty @PathVariable int  id){
        return ResponseEntity.ok(carteIdentitateService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@NotEmpty @PathVariable int id){
        carteIdentitateService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("ID card with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }

    @PutMapping("/{id}")
    public void update(@NotEmpty @PathVariable int id, @Valid @RequestBody IDcardDTO carteIdentitateDTO ){
         carteIdentitateService.update(id, carteIdentitateDTO);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        carteIdentitateService.deleteAll();
    }
}
