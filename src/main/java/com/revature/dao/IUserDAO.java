package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IUserDAO {
	
	public User findById(int id);
	public User findByUsername(String u);
	public U_Role findUserRole(int id);
	public boolean addUser(User u);
	public List<User> findAll();

}
