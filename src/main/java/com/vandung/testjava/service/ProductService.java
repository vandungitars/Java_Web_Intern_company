package com.vandung.testjava.service;

import java.util.List;

import com.vandung.testjava.model.Product;

public interface ProductService {
	public List<Product> getAllProductorLimit(int first);
	public int addProduct(Product product);
	public void updateProduct(int productId, Product product);
	public int deleteProduct(int productId);
}
