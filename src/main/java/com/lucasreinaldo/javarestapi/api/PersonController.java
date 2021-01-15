package com.lucasreinaldo.javarestapi.api;

import com.lucasreinaldo.javarestapi.model.Person;
import com.lucasreinaldo.javarestapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public int addPerson(@RequestBody Person person) {
        return personService.addPerson((person));
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(path = "{id}")
    public Person show(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @PutMapping(path = "{id}")
    public int update(@PathVariable("id") UUID id, @RequestBody @NonNull Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping(path = "{id}")
    public int delete(@PathVariable("id") UUID id) {
        return personService.deletePerson(id);
    }
}
