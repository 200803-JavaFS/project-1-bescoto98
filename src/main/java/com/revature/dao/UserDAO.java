package com.revature.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.U_Role;
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

	@Override
	public U_Role findUserRole(int id) {
		Session ses = HibernateUtil.getSession();
		
		try {
			U_Role ur = ses.get(U_Role.class, id);
			return ur;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
