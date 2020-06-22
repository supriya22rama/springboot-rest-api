package com.peopleten.codechallenge1.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peopleten.codechallenge1.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerJpa;
	
	//to add new customer
	public Customer addCustomer(Customer c) {
		System.out.println("Date :" + c.getDob());
		 return customerJpa.save(c);
	}
	
	//to retrieve existing customer
	public Optional<Customer> retrieveCustomer(long id) {
		return customerJpa.findById(id);
	}
	
}
