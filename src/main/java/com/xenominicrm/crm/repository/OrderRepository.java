package com.xenominicrm.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xenominicrm.crm.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
