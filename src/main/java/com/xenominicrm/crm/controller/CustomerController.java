package com.xenominicrm.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xenominicrm.crm.entity.Customer;
import com.xenominicrm.crm.services.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return "Customer Added Successfully";
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/getCustomerById")
	public Customer getUser(@RequestParam Integer id) {
		return customerService.getCustomerById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
		return "Customer with id "+ id + " Successfully deleted.";
	}

}
