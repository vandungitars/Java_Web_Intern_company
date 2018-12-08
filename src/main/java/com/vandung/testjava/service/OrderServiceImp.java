package com.vandung.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.testjava.model.DetailOrder;
import com.vandung.testjava.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<DetailOrder> getOrderByUser(int idUser) {
		return orderRepository.getOrderByUser(idUser);
	}

}
