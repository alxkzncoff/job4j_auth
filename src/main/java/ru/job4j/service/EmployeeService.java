package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Employee;
import ru.job4j.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository store;

    public EmployeeService(EmployeeRepository store) {
        this.store = store;
    }

    public Employee save(Employee employee) {
        return store.save(employee);
    }

    public Optional<Employee> findById(int id) {
        return store.findById(id);
    }

    public List<Employee> findAll() {
        return store.findAll();
    }

    public void delete(Employee employee) {
        store.delete(employee);
    }
}
