package com.jbeans.employee.controller;

import com.jbeans.employee.entity.Employee;
import com.jbeans.employee.service.Empservice;
import com.jbeans.employee.util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Valid
@RequestMapping("/emp")
public class Empcontrol {

    @Autowired
    Empservice empservice;

    @GetMapping("/all")
   // public List<Employee> getAllEmp(){
    public ResponseEntity<Object> getAllEmp(){
        List<Employee> emplist=empservice.getAllEmpList();
    //    return emplist;
        return ResponseHandler.generateResponse("Successfully fetched the data", HttpStatus.OK,emplist);
    }

    @GetMapping("/top10/{offset}/{size}/{sort}")
    public ResponseEntity<Object> getEmpCache10(@PathVariable int offset,@PathVariable  int size,@PathVariable  String sort){
        List<Employee> emptop10 = empservice.getEmpCatch(offset, size, sort);
        return ResponseHandler.generateResponse("Top 10",HttpStatus.OK,emptop10);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEmp(@Valid @RequestBody Employee emp){
        Employee newemp=empservice.createEmp(emp);
        return ResponseHandler.generateResponse("New Employee Created",HttpStatus.CREATED,newemp);
    }

    @PatchMapping("/business_UnitUpdate/{id}/{business_Unit}")
    public ResponseEntity<Object> business_UnitUpdate(@PathVariable String business_Unit,@PathVariable String id){
        
        Optional<Employee> temp=empservice.partialUpdate(id,business_Unit);
        if(temp.isPresent()) {
            return  ResponseHandler.generateResponse("Update Method Completed", HttpStatus.OK,temp.get());
        }

        return  ResponseHandler.generateResponse("Update Method Completed", HttpStatus.NOT_FOUND,null);

    }

    @DeleteMapping("/clearCache")
    public ResponseEntity<Object> clearCache(){
       String clearMsg= empservice.clearCache();
        return ResponseHandler.generateResponse("Clear Cache", HttpStatus.OK,clearMsg);
    }

    @PutMapping("/updateemp")
    public ResponseEntity<Object> updateEmp(@RequestBody Employee employee){
        Optional<Employee> updatedEmp=empservice.updateEmp(employee);
        if(updatedEmp.isPresent()) {
            return  ResponseHandler.generateResponse("Update Method Completed", HttpStatus.OK,updatedEmp.get());
        }

        return  ResponseHandler.generateResponse("Update Method Completed", HttpStatus.NOT_FOUND,null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmp(@PathVariable String id){
        Optional<Employee> emp=empservice.getEmpId(id);
        return ResponseHandler.generateResponse("Find employee by ID ::",HttpStatus.FOUND,emp);
    }
}