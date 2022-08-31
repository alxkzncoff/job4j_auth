package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8080/persons/";
    private static final String API_ID = "http://localhost:8080/persons/{id}";

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/allAcc/{id}")
    public List<Person> findAllEmployeeAcc(@PathVariable int id) {
        return rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                employeeService.save(employee),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/addAcc/{id}")
    public ResponseEntity<Void> addAccount(@PathVariable int id, @RequestBody Person person) {
        Optional<Employee> employee = employeeService.findById(id);
        employee.ifPresent(e -> e.addPerson(rest.postForObject(API, person, Person.class)));
        employeeService.save(employee.get());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateAcc")
    public ResponseEntity<Void> updateAccount(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employeeService.delete(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAcc/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }

}
