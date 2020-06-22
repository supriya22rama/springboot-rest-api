package com.peopleten.codechallenge1.controller;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Optional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.peopleten.codechallenge1.controller.CustomerController;
import com.peopleten.codechallenge1.model.Customer;
import com.peopleten.codechallenge1.repository.CustomerRepository;
import com.peopleten.codechallenge1.repository.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;
	
	@Test
	public void retrieveCustomer() throws Exception {
		Customer mock = new Customer(1000,"PAVITRA", "RAMA", new Date(), "pavitraR@gmail.com", "password");
		Mockito.when(customerService.retrieveCustomer(Mockito.anyLong())).thenReturn(Optional.of(mock));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/customer/65").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{id:1000,firstname: \"PAVITRA\",lastname: \"RAMA\",dob: \"2020-06-22\",email: \"pavitraR@gmail.com\",password: \"password\"}";
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
	}

	@Test
	public void addCustomer() throws Exception {
		Customer mock = new Customer(1111,"Narendra", "Modi", new Date(), "modi@gmail.com", "ord");
		String customer = "{\"id\":1111,\"firstname: \"Narendra\",lastname: \"Modi\",dob: \"2020-06-22\",email: \"modi@gmail.com\",password: \"password\"}";
		Mockito.when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(mock);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/customer").accept(MediaType.APPLICATION_JSON)
				.content(customer).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("Response : " + response.getStatus());
		assertEquals(HttpStatus.CREATED.value(),response.getStatus());
	}
}
