package com.oauth2example.endpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2example.endpoint.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
