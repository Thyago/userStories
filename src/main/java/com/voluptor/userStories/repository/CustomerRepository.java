package com.voluptor.userStories.repository;

import javax.enterprise.context.ApplicationScoped;

import com.voluptor.userStories.model.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

}
