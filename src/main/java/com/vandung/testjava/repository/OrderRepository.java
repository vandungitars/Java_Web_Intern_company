package com.vandung.testjava.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.testjava.model.DetailOrder;
import com.vandung.testjava.model.Orders;
import com.vandung.testjava.model.User;

@Repository(value = "orderRepository")
public class OrderRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(rollbackFor = Exception.class)
	public List<DetailOrder> getOrderByUser(int idUser) {
		List<DetailOrder> listDetailOrder = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + DetailOrder.class.getName() + " dt where dt.order.orderId in (select o.orderId from " + Orders.class.getName() + " o where o.user.id = :idUser)";
		Query<DetailOrder> query = session.createQuery(sql);
		query.setParameter("idUser", idUser);
		listDetailOrder = query.getResultList();
		return listDetailOrder;
	}
}
