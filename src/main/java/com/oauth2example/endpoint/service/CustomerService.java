package com.oauth2example.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oauth2example.endpoint.entity.Customer;
import com.oauth2example.endpoint.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public Customer findById(Long id){
		return customerRepository.findById(id).get();
	}

}
