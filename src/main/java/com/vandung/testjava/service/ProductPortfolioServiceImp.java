package com.vandung.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vandung.testjava.model.ProductPortfolio;
import com.vandung.testjava.repository.ProductPortfolioRepository;

@Service
public class ProductPortfolioServiceImp implements ProductPortfolioService{
	
	@Autowired
	private ProductPortfolioRepository productPortfolioRepository;

	@Override
	public List<ProductPortfolio> getListPortfolio() {
		return productPortfolioRepository.getListPortfolio();
	}

	@Override
	public ProductPortfolio getPortfolioByName(String portfolioName) {
		return productPortfolioRepository.getPortfolioByName(portfolioName);
	}
}
