package com.management.controllers;

import com.management.models.Employee;
import com.management.service.EmplyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmplyeeService emplyeeService;

    @GetMapping("/Employees")
    public ResponseEntity getAllEmployees(){
        return  emplyeeService.getAllEmployee();
    }

     @GetMapping("/getById/{id}")
     public ResponseEntity getById(@PathVariable long id){
         return emplyeeService.getById(id) ;
     }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody Employee employee){

        return emplyeeService.addEmployee(employee);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable long employeeId){
        return emplyeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee , @PathVariable("employeeId") long employeeId){
        return emplyeeService.updateEmployee(employee,employeeId);
    }
}
