package com.jbeans.employee.service;

import com.jbeans.employee.entity.Employee;
import com.jbeans.employee.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Empservice {

    @Autowired
    EmpRepo emprepo;
    public List<Employee> getAllEmpList(){
        return  emprepo.findAll();
    }

    @Cacheable("top10emp")
    public List<Employee> getEmpCatch(int pagesize, int offset, String field ) {
        System.out.println("########################## Employee Catch ##########################");
        Page<Employee> pages = emprepo.findAll(PageRequest.of(offset, pagesize, Sort.by(Sort.Direction.ASC, field)));
       // return pages;
        return pages.getContent();
    }

    public Employee createEmp(Employee emp) {
        Employee newemp=emprepo.save(emp);
        return newemp;
    }

    @CacheEvict(value = "emp",allEntries = true)
    public String clearCache() {
        String massage="Clear all Entries from emp cache";
        return massage;
    }

    public Optional<Employee> getEmpId(String id) {
        Optional<Employee> emp=emprepo.findById(id);
        return emp;
    }

    public Optional<Employee> updateEmp(Employee employee) {

        Optional<Employee> dbemp=emprepo.findById(employee.getEEID());
        Employee tempemp=null;
        if(dbemp.isPresent()){
            tempemp=dbemp.get();
            tempemp.setAge(employee.getAge());
            tempemp.setCity(employee.getCity());
            tempemp.setCountry(employee.getCountry());
            tempemp.setDepartment(employee.getDepartment());
            tempemp.setEEID(employee.getEEID());
            tempemp.setBonus(employee.getBonus());
            tempemp.setDepartment(employee.getDepartment());
            tempemp.setGender(employee.getGender());
            tempemp.setEthnicity(employee.getEthnicity());
            tempemp.setAnnual_Salary(employee.getAnnual_Salary());
            tempemp.setBusiness_Unit(employee.getBusiness_Unit());
            tempemp.setHire_Date(employee.getHire_Date());
            tempemp.setFull_Name(employee.getFull_Name());
            tempemp.setJob_Title(employee.getJob_Title());
            emprepo.save(tempemp);
        }
        return Optional.ofNullable(tempemp);
    }

    public Optional<Employee> partialUpdate(String id, String business_Unit) {
        Optional<Employee> emp=emprepo.findById(id);
        Employee tempemp=null;
        if(emp.isPresent()){
            tempemp=emp.get();
            tempemp.setBusiness_Unit(business_Unit);
        }
        emprepo.save(tempemp);
        return Optional.ofNullable(tempemp);
    }
}
