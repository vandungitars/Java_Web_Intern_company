package com.vandung.testjava.service;

import java.util.List;
import com.vandung.testjava.model.ProductPortfolio;

public interface ProductPortfolioService {
	public List<ProductPortfolio> getListPortfolio();
	public ProductPortfolio getPortfolioByName(String portfolioName);
}
