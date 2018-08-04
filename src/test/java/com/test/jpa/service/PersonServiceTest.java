package com.test.jpa.service;

import com.test.jpa.entity.Person;
import com.test.jpa.repo.PersonRepository;
import com.test.jpa.service.PersonService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);
    @Autowired
    private PersonService personService;

    @Test
    public void testGetById() {
        Person person = personService.getPersonById(1L);
        LOGGER.debug("Retrieved entity: {}", person);
        Assert.assertNotNull(person);
        person = personService.getPersonById(1000L);
        Assert.assertNull(person);
    }

    @Test
    public void testSavePerson() {
        Person person = new Person();
        person.setId(Long.valueOf(10));
        person.setFullName("Jagrut Shah");
        person.setOccupation("Architect");
        Person savedPerson = personService.save(person);
        Assert.assertEquals(person.getFullName(), savedPerson.getFullName());
    }

    @TestConfiguration
    static class PersonServiceConfiguration {

        @Autowired
        private PersonRepository personRepository;

        @Bean
        public PersonService personService() {
            return new PersonService(personRepository);
        }
    }
}
