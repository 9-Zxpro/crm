package com.xenominicrm.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.xenominicrm.crm.entity.Order;
import com.xenominicrm.crm.repository.OrderRepository;
import com.xenominicrm.crm.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void addOrder(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id "+id));
		return order;
	}

	@Override
	public void deleteorder(Integer id) {
		Order order = orderRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id "+id));
		orderRepository.delete(order);
		
	}

}
