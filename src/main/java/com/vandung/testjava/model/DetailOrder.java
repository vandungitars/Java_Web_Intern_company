package com.vandung.testjava.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detail_order", catalog = "test")
public class DetailOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "detail_order_id", unique = true, nullable = false)
	private Integer detailOrderId;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "total_price")
	private Double totalPrice;
	
	@Column(name = "address_customer")
	private String addressCustomer;
	
	@Column(name = "phone_customer")
	private String phoneCustomer;
	
	@Column(name = "name_product")
	private String nameProduct;
	
	@Column(name = "price_product")
	private String priceProduct;
	
	@Column(name = "date_order")
	private Date dateOrder;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Orders order;

	public Integer getDetailOrderId() {
		return detailOrderId;
	}

	public void setDetailOrderId(Integer detailOrderId) {
		this.detailOrderId = detailOrderId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}

	public String getPhoneCustomer() {
		return phoneCustomer;
	}

	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(String priceProduct) {
		this.priceProduct = priceProduct;
	}

	public DetailOrder() {}

	public DetailOrder(Integer detailOrderId, int count, Double totalPrice, String addressCustomer,
			String phoneCustomer, String nameProduct, String priceProduct, Date dateOrder, Orders order) {
		this.detailOrderId = detailOrderId;
		this.count = count;
		this.totalPrice = totalPrice;
		this.addressCustomer = addressCustomer;
		this.phoneCustomer = phoneCustomer;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.dateOrder = dateOrder;
		this.order = order;
	}
}
