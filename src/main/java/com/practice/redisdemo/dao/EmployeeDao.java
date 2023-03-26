package com.practice.redisdemo.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.redisdemo.model.Employee;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class EmployeeDao {

    @Autowired
    private RedisAsyncCommands<String, String> asyncCommands;

    @Autowired
    private ObjectMapper objectMapper;

    public Boolean saveEmpToDB(Employee emp) throws ExecutionException, InterruptedException, JsonProcessingException {
        return asyncCommands.hset("EMP", String.valueOf(emp.getId()), objectMapper.writeValueAsString(emp)).get();
    }

    public List<Employee> getAllEmpFromDB() throws ExecutionException, InterruptedException {
        Map<String, String>  empMap = asyncCommands.hgetall("EMP").get();
        List<Employee> empList = new ArrayList<>();
        empMap.entrySet().stream().forEach(es -> {
            try {
                empList.add(objectMapper.readValue(es.getValue(), Employee.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return empList;
    }
}
