package com.management.service;

import com.management.models.Employee;
import com.management.repo.EmployeeRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmplyeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmplyeeService employeeService;

    private long id=1;

    private List<Employee> emoloyeeList() {
        return new ArrayList<>(List.of(new Employee(12,"Zeeshan","Software Engineer","Male","9058659709")));
    }
    private Employee employee = new Employee(12,"Zeeshan","Software Engineer","Male","9058659709");

    @Test
    public void getAllusers() {
        when(employeeRepository.findAll()).thenReturn(emoloyeeList());
        ResponseEntity responseEntity = employeeService.getAllEmployee();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllEmployeeException(){

        when(employeeRepository.findAll()).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = employeeService.getAllEmployee();
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getAllUsersEmpty(){

        when(employeeRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity responseEntity = employeeService.getAllEmployee();
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void addUser() {

        when(employeeRepository.save(employee)).thenReturn(employee);
        ResponseEntity responseEntity= employeeService.addEmployee(employee);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void addUserException(){

        when(employeeRepository.save(employee)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = employeeService.addEmployee(employee);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteUserSuccess() {

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        ResponseEntity responseEntity= employeeService.deleteEmployee(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);

    }

    @Test
    public void testDeleteUserNotFound() {

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity responseEntity= employeeService.deleteEmployee(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteUserException(){

        when(employeeRepository.findById(id)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = employeeService.deleteEmployee(id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testUpdateUserSuccess() {

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        ResponseEntity responseEntity= employeeService.updateEmployee(employee,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void testUpdateUserNotFound() {

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity responseEntity= employeeService.updateEmployee(employee,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void UpdateUserException(){

        when(employeeRepository.findById(id)).thenThrow(RuntimeException.class);
        ResponseEntity responseEntity = employeeService.updateEmployee(employee,id);
        Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}