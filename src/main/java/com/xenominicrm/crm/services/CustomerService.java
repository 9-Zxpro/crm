package com.xenominicrm.crm.services;

import java.util.List;

import com.xenominicrm.crm.entity.Customer;

public interface CustomerService {
	void addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Integer id);
	void deleteCustomer(Integer id);
}
