package com.peopleten.codechallenge1.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.peopleten.codechallenge1.exception.CustomerNotFoundException;
import com.peopleten.codechallenge1.model.Customer;
import com.peopleten.codechallenge1.repository.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customer;
	
	@GetMapping(value = "api/customer/{id}")
	public Customer getBookingByCust_Id(@PathVariable long id) {
		return customer.retrieveCustomer(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	
	@PostMapping(value="api/customer")
	public ResponseEntity<Void> addCustomer(@Valid @RequestBody Customer newCustomer){
		Customer savedCustomer = customer.addCustomer(newCustomer);
		if(savedCustomer == null) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        
        return ResponseEntity.created(location).build();
	}

}
