package com.employeesystem.employeesystem.web;

import com.employeesystem.employeesystem.repository.model.carteIdentitate.CarteIdentitate;
import com.employeesystem.employeesystem.service.api.CarteIdentitateService;
import com.employeesystem.employeesystem.service.dto.CarteIdentitateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carteIdentitate")
public class CarteController {
    private CarteIdentitateService carteIdentitateService;

    public CarteController(CarteIdentitateService carteIdentitateService) {
        this.carteIdentitateService = carteIdentitateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarteIdentitate create(@Valid @RequestBody CarteIdentitateDTO carteIdentitateDTO){
        return carteIdentitateService.create(carteIdentitateDTO);
    }

    @GetMapping
    public List<CarteIdentitate> getAll(){
        return carteIdentitateService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteIdentitate> getById(@NotEmpty @PathVariable int  id){
        return ResponseEntity.ok(carteIdentitateService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@NotEmpty @PathVariable int id){
        carteIdentitateService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@NotEmpty @PathVariable int id, @Valid @RequestBody CarteIdentitateDTO carteIdentitateDTO ){
         carteIdentitateService.update(id, carteIdentitateDTO);
    }
}
