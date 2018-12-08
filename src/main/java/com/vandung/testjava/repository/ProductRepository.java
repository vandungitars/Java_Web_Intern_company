package com.vandung.testjava.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.testjava.model.Orders;
import com.vandung.testjava.model.Product;
import com.vandung.testjava.model.User;

@Repository(value = "productRepository")
public class ProductRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional(rollbackFor = Exception.class)
	public List<Product> getAllProductorLimit(int first) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> listProduct = new ArrayList<>();
		if(first < 0){
			String sql = "from " + Product.class.getName();
			Query<Product> query = session.createQuery(sql);
			listProduct = query.getResultList();
		}
		else{
			String sql = "from " + Product.class.getName();
			Query<Product> query = session.createQuery(sql).setFirstResult(first).setMaxResults(10);
			listProduct = query.getResultList();
		}
		return listProduct;
	}

	@Transactional(rollbackFor = Exception.class)
	public int addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
		return 0;
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateProduct(int productId, Product product) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from " + Product.class.getName() + " p where p.productId = :productId";
		Query<Product> query = session.createQuery(sql);
		query.setParameter("productId", productId);
		Product productDB = query.getSingleResult();
		if(product.getProductName() != null)
			productDB.setProductName(product.getProductName());
		if(product.getProductPrice() != 0.0d)
			productDB.setProductPrice(product.getProductPrice());
		if(product.getProductDescription() != null)
			productDB.setProductDescription(product.getProductDescription());
		if(product.getProductFeature() != null)
			productDB.setProductFeature(product.getProductFeature());
		if(product.getProductImage() != null)
			productDB.setProductImage(product.getProductImage());
		if(product.getProductPortfolio() != null)
			productDB.setProductPortfolio(product.getProductPortfolio());
		session.update(productDB);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int deleteProduct(int productId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "delete " + Product.class.getName() + " p where p.productId = :Id";
		Query<Product> query = session.createQuery(sql);
		query.setParameter("Id", productId);
		return query.executeUpdate();
	}
}
