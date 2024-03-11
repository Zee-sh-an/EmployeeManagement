package com.management.controllers;

import com.management.models.Employee;
import com.management.service.EmplyeeService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Mock
    private EmplyeeService emplyeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void getAllUsers() {

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(emplyeeService.getAllEmployee()).thenReturn(responseEntity);
        ResponseEntity responseEntity1 = employeeController.getAllEmployees();
        Assert.assertEquals(responseEntity1.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    public void addUser() {

        Employee employee = new Employee(12,"Zeeshan","Software Engineer","Male","9058659709");

        ResponseEntity responseEntity =new ResponseEntity<>(HttpStatus.OK);
        when(employeeController.addUser(employee)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= emplyeeService.addEmployee(employee);
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void deleteUser() {

        long id=1;

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(emplyeeService.deleteEmployee(id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= employeeController.deleteEmployee(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }
    @Test
    public void getById() {

        long id=1;

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(emplyeeService.getById(id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= employeeController.getById(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void updateUser(){
        long id=1;
        Employee employee = new Employee(12,"Zeeshan","Software Engineer","Male","9058659709");

        ResponseEntity responseEntity=new ResponseEntity<>(HttpStatus.OK);
        when(emplyeeService.updateEmployee(employee,id)).thenReturn(responseEntity);
        ResponseEntity responseEntity1= employeeController.updateEmployee(employee,id);
        Assert.assertEquals(responseEntity1.getStatusCode(),HttpStatus.OK);
    }

}