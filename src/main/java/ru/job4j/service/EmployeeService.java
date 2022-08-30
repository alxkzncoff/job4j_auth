package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Employee save(Employee employee) {
        return store.save(employee);
    }

    @Transactional
    public Optional<Employee> findById(int id) {
        return store.findById(id);
    }

    @Transactional
    public List<Employee> findAll() {
        return store.findAll();
    }

    @Transactional
    public void delete(Employee employee) {
        store.delete(employee);
    }
}
