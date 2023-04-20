package com.practice.redisdemo.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.redisdemo.model.Employee;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Repository
public class EmployeeDao {

    @Autowired
    private RedisAsyncCommands<String, String> asyncCommands;

    @Autowired
    private ObjectMapper objectMapper;

    public void saveEmpToDB(Employee emp) throws ExecutionException, InterruptedException, JsonProcessingException {
        asyncCommands.set(String.valueOf(emp.getId()), objectMapper.writeValueAsString(emp)).get();
    }

    public void saveEmpToDBWithExpiry(Employee emp, long expiry) throws ExecutionException, InterruptedException, JsonProcessingException {
        asyncCommands.set(String.valueOf(emp.getId()), objectMapper.writeValueAsString(emp)).get();
        asyncCommands.expire(String.valueOf(emp.getId()), Duration.ofMillis(expiry));
    }

    public Employee getEmpFromDB(String id) throws ExecutionException, InterruptedException, JsonProcessingException {
        return objectMapper.readValue(asyncCommands.get(id).get(), Employee.class);
    }

    public void deleteEmpToDB(String id) throws ExecutionException, InterruptedException {
        long result =  asyncCommands.del(id).get();
        System.out.println("delete res-"+result);
    }
}
