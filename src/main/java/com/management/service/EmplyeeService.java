package com.management.service;

import com.management.models.Employee;
import com.management.models.Error;
import com.management.repo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmplyeeService {

    private final Logger logger= LoggerFactory.getLogger(EmplyeeService.class);


    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity getAllEmployee() {

        try {
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("there is no employee in db").build();
                logger.info("employee not Found",employees);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            logger.info("All employees here",employees);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retriving data").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getById(long id) {

        try {
            Optional<Employee> employeeById = employeeRepository.findById(id);
            if (employeeById.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("there is no employee in db with this id").build();
                logger.info("employee not Found",employeeById);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            logger.info("employee found",employeeById);
            return new ResponseEntity<>(employeeById, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retriving data").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity  addEmployee(Employee employee) {

        try {
            Employee user1 = employeeRepository.save(employee);
            logger.info("employee add successfully",user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("adding use Employee failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity deleteEmployee(long id) {

        try {

            Optional<Employee> employeeById = employeeRepository.findById(id);

            if (employeeById.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("there is no employee of this id").build();
                logger.info("employee not Found",employeeById);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            employeeRepository.deleteById(id);
            Error error = Error.builder().code(HttpStatus.OK.getReasonPhrase()).message("Deleted Successfully").build();
            logger.info("employee deleted",error);
            return new ResponseEntity<>(error, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Deletion of employee failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateEmployee(Employee employee, long id) {

        try {
            Optional<Employee> employeeById = employeeRepository.findById(id);
            if (employeeById.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("Employee not found with this id").build();
                logger.info("employee not Found",employeeById);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }

            employeeById.get().setId(id);
            Employee updatedEmployee = employeeRepository.save(employee);
            logger.info("employee updated",updatedEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Employee updation Failed").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
