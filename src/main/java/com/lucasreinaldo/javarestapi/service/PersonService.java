package com.lucasreinaldo.javarestapi.service;

import com.lucasreinaldo.javarestapi.dao.PersonDAO;
import com.lucasreinaldo.javarestapi.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(@Qualifier("fakeDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public int addPerson(Person person) {
        return personDAO.insertPerson(person);
    }

    public List<Person> getAll() {
        return personDAO.selectAll();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDAO.getPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDAO.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person person) {
        return personDAO.updatePerson(id, person);
    }
}
