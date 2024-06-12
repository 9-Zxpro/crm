package com.xenominicrm.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xenominicrm.crm.entity.Customer;
import com.xenominicrm.crm.repository.CustomerRepository;
import com.xenominicrm.crm.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id "+id));
		return customer;
		
	}

	@Override
	public void deleteCustomer(Integer id) {
		Customer customer = customerRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id "+id));
		customerRepository.delete(customer);
	}
	

}
