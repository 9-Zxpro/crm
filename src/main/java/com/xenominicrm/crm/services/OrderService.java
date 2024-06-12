package com.xenominicrm.crm.services;

import java.util.List;

import com.xenominicrm.crm.entity.Order;

public interface OrderService {
	void addOrder(Order order);
	
	List<Order> getAllOrders();
	
	Order getOrderById(Integer id);
	
	void deleteorder(Integer id);
}
