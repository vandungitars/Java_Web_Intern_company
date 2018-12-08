package com.vandung.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.testjava.model.Product;
import com.vandung.testjava.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProductorLimit(int first) {
		return productRepository.getAllProductorLimit(first);
	}

	@Override
	public int addProduct(Product product) {
		return productRepository.addProduct(product);
	}

	@Override
	public void updateProduct(int productId, Product product) {
		productRepository.updateProduct(productId, product);
	}

	@Override
	public int deleteProduct(int productId) {
		return productRepository.deleteProduct(productId);
	}
}
