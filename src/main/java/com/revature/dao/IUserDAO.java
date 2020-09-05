package com.revature.dao;

import com.revature.models.*;

public interface IUserDAO {
	
	public User findById(int id);
	public User findByUsername(String u);
	public U_Role findUserRole(int id);

}
