package com.oauth2example.endpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2example.endpoint.entity.Customer;
import com.oauth2example.endpoint.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("customers")
	public List<Customer> findAll(){
		return customerService.findAll();
	}
	
	@GetMapping("customers/{id}")
	public Customer findById(@PathVariable Long id) {
		return customerService.findById(id);
	}

}
