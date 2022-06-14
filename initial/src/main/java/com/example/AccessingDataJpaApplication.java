package com.example;

import com.example.model.Customer;
import com.example.services.CustomerCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;


@SpringBootApplication
public class AccessingDataJpaApplication {
    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerCrudRepository customerCrudRepository) {
        return (args) -> {
            customerCrudRepository.save(new Customer("Jack", "Brown"));
            customerCrudRepository.save(new Customer("Chloe", "Bush"));
            customerCrudRepository.save(new Customer("Bob", "Bobob"));
            customerCrudRepository.save(new Customer("Bob", "Test"));

            log.info("findAll:");
            for (Customer customer : customerCrudRepository.findAll()) {
                log.info(customer.toString());
            }

            log.info("findById:");
            Optional<Customer> customer = customerCrudRepository.findById(1L);
            log.info(customer.toString());

            log.info("existById:");
            log.info(String.valueOf(customerCrudRepository.existsById(2L)));
            log.info(String.valueOf(customerCrudRepository.existsById(4L)));

            log.info("findByLastNameAndFirstName:");
            for (Customer customersByLastNameAndFirstName : customerCrudRepository.findByLastNameAndFirstName("Bobob", "Bob")) {
                log.info(customersByLastNameAndFirstName.toString());
            }

            log.info("countByFirstName:");
            log.info(String.valueOf(customerCrudRepository.countByFirstName("Bob")));

        };
    }
}
