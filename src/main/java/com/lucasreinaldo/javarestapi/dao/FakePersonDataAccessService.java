package com.lucasreinaldo.javarestapi.dao;

import com.lucasreinaldo.javarestapi.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakePersonDataAccessService implements PersonDAO {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAll() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> getPerson = getPersonById(id);
        if(getPerson.isEmpty()) {
            return 0;
        }

        DB.remove(getPerson.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return getPersonById(id).map(p -> {
                    int indexPersonToDelete = DB.indexOf(p);
                    if (indexPersonToDelete >= 0) {
                        DB.set(indexPersonToDelete, new Person(id, person.getName()));
                        return 1;
                    }
                    return 0;
            }).orElse(0);
    }
}
