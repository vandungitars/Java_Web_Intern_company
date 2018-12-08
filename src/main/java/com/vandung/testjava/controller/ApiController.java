package com.vandung.testjava.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandung.testjava.model.Product;
import com.vandung.testjava.model.User;
import com.vandung.testjava.service.ProductPortfolioService;
import com.vandung.testjava.service.ProductService;
import com.vandung.testjava.service.UserService;

@RestController
public class ApiController {

	@Autowired
	private ProductPortfolioService productPortfolioService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
    private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private UserService userService;
	 
	public static String nameFile;
	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestParam String dataJson){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		User user = new User();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			user.setUsername(jsonNode.get("username").asText());
			user.setPassword(passwordEncoder.encode(jsonNode.get("password").asText()));
			userService.createUser(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/uploadfile", method = RequestMethod.POST)
	public void UploadFile(MultipartHttpServletRequest request)
	{
		String path_save_file = "E:\\WorkSpace\\com.vandung.testjava\\src\\main\\resources\\static\\image\\";
		Iterator<String> listNames = request.getFileNames();
		MultipartFile mpf = request.getFile(listNames.next());
		File file_save = new File(path_save_file + mpf.getOriginalFilename());
		nameFile = mpf.getOriginalFilename();
		try {
			mpf.transferTo(file_save);
		} catch (IllegalStateException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/addProduct", method = RequestMethod.POST)
	public void addProduct(@RequestParam String dataJson, @RequestParam String optionText){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Product product = new Product();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			product.setProductPrice(jsonNode.get("price").asDouble());
			product.setProductName(jsonNode.get("name").asText());
			product.setProductImage(nameFile);
			product.setProductFeature(jsonNode.get("feature").asText());
			product.setProductDescription(jsonNode.get("description").asText());
			product.setProductPortfolio(productPortfolioService.getPortfolioByName(optionText));
			productService.addProduct(product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/updateProduct", method = RequestMethod.POST)
	public void updateProduct(@RequestParam String dataJson, @RequestParam String optionText, @RequestParam String idProduct){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		Product product = new Product();
		try {
			jsonNode = objectMapper.readTree(dataJson);
			product.setProductPrice(jsonNode.get("price").asDouble());
			product.setProductName(jsonNode.get("name").asText());
			product.setProductImage(nameFile);
			product.setProductFeature(jsonNode.get("feature").asText());
			product.setProductDescription(jsonNode.get("description").asText());
			product.setProductPortfolio(productPortfolioService.getPortfolioByName(optionText));
			productService.updateProduct(Integer.parseInt(idProduct),product);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/api/deleteProduct", method = RequestMethod.POST)
	public void deleteProduct(@RequestParam String idProduct){
		productService.deleteProduct(Integer.parseInt(idProduct));
	}
	
	@RequestMapping(value = "/api/disableUser", method = RequestMethod.POST)
	public void disableUser(@RequestParam String idUser, @RequestParam String enabled){
		userService.disableUser(Integer.parseInt(idUser), enabled);
	}
}
