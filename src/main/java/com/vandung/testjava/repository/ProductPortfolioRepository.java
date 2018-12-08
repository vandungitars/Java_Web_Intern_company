package com.vandung.testjava.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.testjava.model.ProductPortfolio;
import com.vandung.testjava.model.User;

@Repository(value = "productPortfolioRepository")
public class ProductPortfolioRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(rollbackFor = Exception.class)
	public List<ProductPortfolio> getListPortfolio(){
		Session session = sessionFactory.getCurrentSession();
		List<ProductPortfolio> listProductPortfolio = new ArrayList<>();
		String sql = "from " + ProductPortfolio.class.getName();
		Query<ProductPortfolio> query = session.createQuery(sql);
		listProductPortfolio = query.getResultList();
		return listProductPortfolio;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ProductPortfolio getPortfolioByName(String Name){
		Session session = sessionFactory.getCurrentSession();
		ProductPortfolio portfolio = new ProductPortfolio();
		String sql = "from " + ProductPortfolio.class.getName() + " p where p.portfolioName = :Name";
		Query<ProductPortfolio> query = session.createQuery(sql);
		query.setParameter("Name", Name);
		portfolio = query.getSingleResult();
		return portfolio;
	}
}
