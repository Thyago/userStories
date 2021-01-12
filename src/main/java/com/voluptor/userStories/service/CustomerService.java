package com.voluptor.userStories.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import com.voluptor.userStories.exception.AlreadyExistsException;
import com.voluptor.userStories.model.Customer;
import com.voluptor.userStories.repository.CustomerRepository;

@ApplicationScoped
public class CustomerService {
	@Inject
	CustomerRepository repository;
	
	public Customer get(Long id) throws NotFoundException
	{
		Customer customer = repository.findById(id);
		if (customer == null) {
			throw new NotFoundException();
		}
		return customer;
	}
	
	@Transactional
	public Customer create(Long id) throws Exception
	{
		Customer customer = repository.findById(id);
		if (customer != null) {
			throw new AlreadyExistsException();
		}
		
		customer = new Customer();
		customer.setId(id);

		repository.persist(customer);

		return customer;
	}
	
	@Transactional
	public void delete(Customer customer)
	{
		repository.delete(customer);
	}
}
