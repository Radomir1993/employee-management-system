package org.radoslawzerek.employeesystem.service;

import org.radoslawzerek.employeesystem.entity.Employee;
import org.radoslawzerek.employeesystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void addEmp(Employee employee) {
        repository.save(employee);
    }

    public Employee getEmpById(Long id) {
        Optional<Employee> e = repository.findById(id);
        if (e.isPresent()) {
            return e.get();
        }
        return null;
    }

    public void deleteEmp(Long id) {
        repository.deleteById(id);
    }

    public Page<Employee> getEmpByPaginate(int currentPage, int size) {
        Pageable p = PageRequest.of(currentPage, size);
        return repository.findAll(p);
    }

}
