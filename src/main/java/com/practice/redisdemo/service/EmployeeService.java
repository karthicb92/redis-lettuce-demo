package com.practice.redisdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.redisdemo.dao.EmployeeDao;
import com.practice.redisdemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public boolean saveEmp(Employee emp) throws ExecutionException, InterruptedException, JsonProcessingException {
        return dao.saveEmpToDB(emp);

    }

    public List<Employee> getAllEmp() throws ExecutionException, InterruptedException {
        return dao.getAllEmpFromDB();

    }
}
