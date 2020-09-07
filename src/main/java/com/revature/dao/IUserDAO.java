package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IUserDAO {
	
	public User findById(int id);
	public User findByUsername(String u);
	public boolean addUser(User u);
	public List<User> findAll();

}
