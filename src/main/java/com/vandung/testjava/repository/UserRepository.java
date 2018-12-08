package com.vandung.testjava.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandung.testjava.model.Role;
import com.vandung.testjava.model.User;
import com.vandung.testjava.model.UsersRoles;

@Repository(value = "userRepository")
public class UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(rollbackFor = Exception.class)
	public User loadUserByUsername(String username) {
		List<User> users = new ArrayList<User>();
		String sql = "from " + User.class.getName() + " where username=:username";
		Session session = this.sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery(sql).setParameter("username", username);
		users = query.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void createUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		String sql = "from " + User.class.getName() + " u where u.id not in (select ur.users from " +  UsersRoles.class.getName() + " ur where ur.role = 1)";
		Session session = this.sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery(sql);
		users = query.getResultList();
		return users;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User getUserById(int idUser) {
		User user = new User();
		String sql = "from " + User.class.getName() + " u where u.id = :idUser";
		Session session = this.sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery(sql);
		query.setParameter("idUser", idUser);
		user = query.getSingleResult();
		return user;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Role getRoleById(int idRole) {
		Role role = new Role();
		String sql = "from " + Role.class.getName() + " r where r.id = :idRole";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery(sql);
		query.setParameter("idRole", idRole);
		role = query.getSingleResult();
		return role;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void disableUser(int idUser, String enabled) {
		if(enabled.equals("true")) {
			String sql1 = "update " + User.class.getName() + " u set u.enabled = 0 where u.id = :idUser";
			Session session = this.sessionFactory.getCurrentSession();
			Query<User> query1 = session.createQuery(sql1);
			query1.setParameter("idUser", idUser);
			query1.executeUpdate();
			
			String sql2 = "delete " + UsersRoles.class.getName() + " ur where ur.users.id = :idUser";
			Session session2 = this.sessionFactory.getCurrentSession();
			Query<User> query2 = session.createQuery(sql2);
			query2.setParameter("idUser", idUser);
			query2.executeUpdate();
		}
		else {
			String sql1 = "update " + User.class.getName() + " u set u.enabled = 1 where u.id = :idUser";
			Session session = this.sessionFactory.getCurrentSession();
			Query<User> query1 = session.createQuery(sql1);
			query1.setParameter("idUser", idUser);
			query1.executeUpdate();		
			User u = new User();
			u = getUserById(idUser);
			Role r = new Role();
			r = getRoleById(2);
			UsersRoles ur = new UsersRoles();
			ur.setUsers(u);
			ur.setRole(r);
			//String sql2 = "insert into " + UsersRoles.class.getName() + " (users.id, role.id) values (:idUser, :idRole)";
			Session session2 = this.sessionFactory.getCurrentSession();
			//Query<User> query2 = session.createQuery(sql2);
			//query2.setParameter("idUser", idUser);
			//query2.setParameter("idRole", 2);
			//query2.executeUpdate();
			session2.save(ur);
		}
	}
}
