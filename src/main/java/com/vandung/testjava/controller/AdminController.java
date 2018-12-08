package com.vandung.testjava.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.vandung.testjava.model.DetailOrder;
import com.vandung.testjava.model.Product;
import com.vandung.testjava.service.OrderService;
import com.vandung.testjava.service.ProductPortfolioService;
import com.vandung.testjava.service.ProductService;
import com.vandung.testjava.service.UserDetailsServiceImp;

@Controller
public class AdminController {

	@Autowired
	private ProductPortfolioService productPortfolioService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserDetailsServiceImp userDetailsService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/admin")
	public String admin() {	
		return "admin";
	}
	
	@RequestMapping("/admin/product")
	public String adminProduct(ModelMap modelMap) {
		getData(modelMap);
		return "adminProduct";
	}
	
	@RequestMapping("/admin/user")
	public String adminUser(ModelMap modelMap) {
		getData(modelMap);
		return "adminUser";
	}
	
	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
	public String orderByUser(ModelMap modelMap, @PathVariable String id) {
		List<DetailOrder> listDetailOrder = orderService.getOrderByUser(Integer.parseInt(id));
		modelMap.addAttribute("listDetailOrder", listDetailOrder);
		return "orderByUser";
	}
	
	public void getData(ModelMap modelMap) {
		List<Product> listProduct = productService.getAllProductorLimit(-1);
		listProduct.forEach((p)->{
			if(!p.getProductImage().contains("/image/")) {
				p.setProductImage("/image/" + p.getProductImage());
			}
		});
		modelMap.addAttribute("listPortfolio", productPortfolioService.getListPortfolio());
		modelMap.addAttribute("listProduct", listProduct);	
		modelMap.addAttribute("listUser", userDetailsService.getAllUser());
	}
	
}
