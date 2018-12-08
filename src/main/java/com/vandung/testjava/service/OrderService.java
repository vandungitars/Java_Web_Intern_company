package com.vandung.testjava.service;

import java.util.List;
import com.vandung.testjava.model.DetailOrder;

public interface OrderService {
	public List<DetailOrder> getOrderByUser(int idUser);
}
