package com.practice.redisdemo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.redisdemo.dao.EmployeeDao;
import com.practice.redisdemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public void saveEmp(Employee emp) throws ExecutionException, InterruptedException, JsonProcessingException {
        dao.saveEmpToDB(emp);
    }

    public void saveEmpWithExpiry(Employee emp, long expiry) throws ExecutionException, InterruptedException, JsonProcessingException {
        dao.saveEmpToDBWithExpiry(emp, expiry);
    }

    public Employee getEmpById(String id) throws ExecutionException, InterruptedException, JsonProcessingException {
        return dao.getEmpFromDB(id);
    }

    public void deleteEmpById(String id) throws ExecutionException, InterruptedException {
        dao.deleteEmpToDB(id);
    }
}
