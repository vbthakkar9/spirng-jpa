package com.test.jpa.com.my.jpa;

import com.test.jpa.Application;
import com.test.jpa.entity.Person;
import com.test.jpa.service.PersonService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class})
public class PersonResourcesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResourcesTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonService personService;

    @Test
    public void testGetById() {
        Person person = restTemplate.getForEntity("/person/1", Person.class).getBody();
        LOGGER.debug("Retrieved entity: {}", person.toString());
        Assert.assertNotNull(person);
    }

    @Test
    public void testSave() {
        Person person = new Person();
        person.setFullName("Chandresh Shah");
        person.setOccupation("Analyst");
        HttpEntity<Person> httpEntity = new HttpEntity<>(person);
        Person response = restTemplate.exchange("/person", HttpMethod.PUT, httpEntity, Person.class).getBody();
        LOGGER.debug("Saved entity: {}", response.toString());
        Assert.assertNotNull(response);
    }
}
