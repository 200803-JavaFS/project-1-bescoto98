package com.revature.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDAO implements IUserDAO {
	
	public UserDAO() {
		super();
	}

	@Override
	public User findById(int id) {
		Session ses = HibernateUtil.getSession();
	
		try {
			User u = ses.get(User.class, id);
			return u;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public User findByUsername(String u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			User us = (User) ses.createQuery("FROM User WHERE u_username =" + u,User.class);
			return us;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
