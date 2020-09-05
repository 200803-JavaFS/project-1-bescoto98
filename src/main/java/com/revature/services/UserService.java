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
	
	public UserDTO findUser(String username) {
		User temp = u.findByUsername(username);
		
		UserDTO user = new UserDTO();
		
		user.id = temp.getU_id();
		user.username = temp.getU_username();
		user.name = temp.getU_fn().concat(" "+temp.getU_ln());
		user.email = temp.getU_email();
		user.role = temp.getU_user_role().getUr_id();
		
		return user;
		
	}
}
