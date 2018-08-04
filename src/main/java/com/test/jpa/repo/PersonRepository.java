package com.test.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.jpa.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
