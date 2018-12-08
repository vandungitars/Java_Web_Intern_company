package com.vandung.testjava.service;

import java.util.List;

import com.vandung.testjava.model.Role;
import com.vandung.testjava.model.User;

public interface UserService {

	public List<User> getAllUser();
	public void createUser(User user);
	public User getUserById(int idUser);
	public Role getRoleById(int idRole);
	public void disableUser(int idUser, String enabled);
}
