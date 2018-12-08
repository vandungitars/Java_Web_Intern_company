package com.vandung.testjava.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_portfolio", catalog = "test")
public class ProductPortfolio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "portfolio_id", unique = true, nullable = false)
	private Integer portfolioId;
	
	@Column(name = "portfolio_name")
	private String portfolioName;

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public ProductPortfolio() {}

	public ProductPortfolio(Integer portfolioId, String portfolioName) {
		this.portfolioId = portfolioId;
		this.portfolioName = portfolioName;
	}
}
