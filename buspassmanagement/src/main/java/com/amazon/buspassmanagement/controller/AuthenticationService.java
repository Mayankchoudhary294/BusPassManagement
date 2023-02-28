package com.amazon.buspassmanagement.controller;

import java.util.List;

import com.amazon.buspassmanagement.db.UserDAO;
import com.amazon.buspassmanagement.model.User;

public class AuthenticationService {
	
	private static AuthenticationService service = new AuthenticationService();
	UserDAO dao = new UserDAO();


	
	public static AuthenticationService getInstance() {
		return service;
	}
		
	public boolean loginUser(User user) {
				
		String sql = "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
		List<User> users = dao.retrieve(sql);
		
		if(users.size() > 0) {
			User u = users.get(0);
			user.id = u.id;
			user.name = u.name;
			user.phone = u.phone;
			user.email = u.email;
			user.address = u.address;
			user.department = u.department;
			user.type = u.type;
			user.createdOn = u.createdOn;
			return true;
		}
		
		return false; 
	}
	
	public boolean registerUser(User user) {
		//int result = dao.insert(user);
		//return result > 0;
		return dao.insert(user) > 0;
	}
	
	public boolean updateUser(User user) {
		return dao.update(user) > 0;
	}
	
}
