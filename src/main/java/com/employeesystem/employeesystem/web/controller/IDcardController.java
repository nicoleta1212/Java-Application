package com.employeesystem.employeesystem.web.controller;

import com.employeesystem.employeesystem.repository.model.IDcard.IDcard;
import com.employeesystem.employeesystem.service.api.IDcardService;
import com.employeesystem.employeesystem.service.dto.IDcardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/IDcard")
public class IDcardController {
    @Autowired
    private IDcardService iDcardService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IDcard create(@Valid @RequestBody IDcardDTO iDcardDTO){
        return iDcardService.create(iDcardDTO);
    }

    @GetMapping
    public List<IDcard> getAll(){
        return iDcardService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IDcard> getById(@NotEmpty @PathVariable String  id){
        return ResponseEntity.ok(iDcardService.getById(id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@NotEmpty @PathVariable String id){
        iDcardService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("ID card with id: " + id + " has been deleted:", Boolean.TRUE);

        return response;
    }

    @PutMapping("/{id}")
    public void update(@NotEmpty @PathVariable String id, @Valid @RequestBody IDcardDTO iDcardDTO ){
         iDcardService.update(id, iDcardDTO);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        iDcardService.deleteAll();
    }
}
