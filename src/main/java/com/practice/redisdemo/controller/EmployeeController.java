package com.practice.redisdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.redisdemo.model.Employee;
import com.practice.redisdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @PostMapping("/emp")
    public ResponseEntity<String> save(@RequestBody Employee emp){
        try {
            empService.saveEmp(emp);
            return ResponseEntity.ok("Saved successfully!!!");
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/emp/{expiryinmillis}")
    public ResponseEntity<String> save(@RequestBody Employee emp, @PathVariable String expiryinmillis){
        try {
            empService.saveEmpWithExpiry(emp, Long.valueOf(expiryinmillis));
            return ResponseEntity.ok("Saved successfully!!!");
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/emp/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable String id){
        try {
            return ResponseEntity.ok(empService.getEmpById(id));
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/emp/{id}")
    public ResponseEntity<String> deleteEmpById(String id) {
        try {
            empService.deleteEmpById(id);
            return ResponseEntity.ok("Deleted successfully!!!");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
