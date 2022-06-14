package com.example.services;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCrudRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);

    @Query("select count(c) from Customer c where c.firstName = ?1")
    long countByFirstName(String firstName);

    List<Customer> findByLastNameAndFirstName(String lastName, String firstName);

}
