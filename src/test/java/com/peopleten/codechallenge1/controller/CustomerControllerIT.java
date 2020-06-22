package com.peopleten.codechallenge1.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.peopleten.codechallenge1.Codechallenge1Application;
import com.peopleten.codechallenge1.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Codechallenge1Application.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Integration test
public class CustomerControllerIT {

	TestRestTemplate template = new TestRestTemplate();
	HttpHeaders header = new HttpHeaders();
	
	@LocalServerPort
	private int port;
	
	@Before
	public void before() {
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	}
	
	public String createURIWithPort(String url) {
		return "http://localhost:"+port+url;
	}
	@Test
	public void getCustomerDetails() throws JSONException {	
		String url =createURIWithPort("/api/customer/10000");
		HttpEntity<String> entity = new HttpEntity<String>(null,header);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
		String expected = "{id: 10000,firstname: \"PAVITRA\",lastname: \"RAMA\",dob: \"2020-06-21\",email: \"pavitraR@gmail.com\",password: \"password\"}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void addCustomerDetails() throws JSONException {	
		String url = createURIWithPort("/api/customer");
		Customer customer = new Customer("SUNDER","PICHAI", new Date(), "pichaiyy555yy@gmail.com", "password");
		HttpEntity<Customer> entity = new HttpEntity<Customer>(customer,header);
		ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertTrue(actual.contains("/api/customer/"));
	}
}
