package com.practice.redisdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.redisdemo.model.Employee;
import com.practice.redisdemo.service.EmployeeService;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private RedisAsyncCommands<String, String> asyncCommands;

    @PostMapping("/emp")
    public ResponseEntity<String> save(@RequestBody Employee emp){
        try {
            boolean isSaved = empService.saveEmp(emp);
            if(isSaved)
                return ResponseEntity.ok("Saved successfully!!!");
            else
                return ResponseEntity.ok("Save failed");
        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/emps")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try {
            return ResponseEntity.ok(empService.getAllEmp());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
