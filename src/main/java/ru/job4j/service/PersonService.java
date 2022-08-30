package ru.job4j.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository store;

    public PersonService(PersonRepository store) {
        this.store = store;
    }

    public Person save(Person person) {
        return store.save(person);
    }

    @Transactional
    public Optional<Person> findById(int id) {
        return store.findById(id);
    }

    @Transactional
    public List<Person> findAll() {
        return store.findAll();
    }

    @Transactional
    public void delete(Person person) {
        store.delete(person);
    }
}
