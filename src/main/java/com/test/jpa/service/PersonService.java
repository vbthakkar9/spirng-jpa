package com.test.jpa.service;

import com.test.jpa.entity.Person;
import com.test.jpa.repo.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Person getPersonById(Long id) {
        return personRepository.findOne(id);
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

}
