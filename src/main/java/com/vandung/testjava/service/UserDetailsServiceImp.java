package com.vandung.testjava.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vandung.testjava.model.User;
import com.vandung.testjava.model.Role;
import com.vandung.testjava.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		User user = userRepository.loadUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, user.getAuthorities());
	}
	
	@Override
	public List<User> getAllUser() {
		return userRepository.getAllUser();
	}
	
	@Override
	public void disableUser(int id, String enabled) {
		userRepository.disableUser(id, enabled);
	}

	@Override
	public User getUserById(int idUser) {
		return userRepository.getUserById(idUser);
	}

	@Override
	public Role getRoleById(int idRole) {
		return userRepository.getRoleById(idRole);
	}

	@Override
	public void createUser(User user) {
		userRepository.createUser(user);
	}
}
