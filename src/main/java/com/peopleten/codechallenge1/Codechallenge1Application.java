package com.peopleten.codechallenge1;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peopleten.codechallenge1.model.Customer;

import com.peopleten.codechallenge1.repository.CustomerService;

@SpringBootApplication
public class Codechallenge1Application implements CommandLineRunner{

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(Codechallenge1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer1 = customerService.addCustomer(new Customer("PAVITRA", "RAMA", new Date(), "pavitraR@gmail.com", "password"));
		Customer customer2 = customerService.addCustomer(new Customer("SUPRIYA", "RAMA", new Date(), "supriyaR@gmail.com", "password"));
		Customer customer3 = customerService.addCustomer(new Customer("SHWETHA", "RAMA", new Date(), "shwethaR@gmail.com", "password"));
	}

}
