package com.revature.services;

import com.revature.dao.*;
import com.revature.models.*;

public class UserService {

	private static IUserDAO u = new UserDAO();
	
	public User findById(int id) {
		return u.findById(id);
	}
	
	public User findByUsername(String s) {
		return u.findByUsername(s);
	}
}
