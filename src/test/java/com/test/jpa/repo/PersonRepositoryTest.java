package com.test.jpa.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.jpa.entity.Person;
import com.test.jpa.repo.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testGetById() {
        Person person = personRepository.findOne(1L);
        LOGGER.debug("retrieved entity: {}", person.toString());
        Assert.assertNotNull(person);
        Assert.assertNotNull(person.getId());
        Assert.assertNotNull(person.getFullName());
        Assert.assertNotNull(person.getOccupation());
    }

    @Test
    public void testSave() {
        Person person = new Person();
        person.setFullName("Sonali Doshi");
        person.setOccupation("House Wife");
        LOGGER.debug("before saving entity: {}", person.toString());
        person = personRepository.save(person);
        Assert.assertNotNull(person);
        Assert.assertNotNull(person.getId());
        LOGGER.debug("after saving entity: {}", person.toString());
    }

    @Test
    public void testNullNameSave() {
        try {
            Person person = new Person();
            personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            LOGGER.debug("Expected Exception", e);
        }
    }
}
