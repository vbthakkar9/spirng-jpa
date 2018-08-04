package com.test.jpa.rest;

import com.test.jpa.entity.Person;
import com.test.jpa.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "person")
public class PersonResources {

    private final PersonService personService;

    @Autowired
    public PersonResources(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @RequestMapping(value = "/persistPerson", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity savePerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.save(person));
    }
}
