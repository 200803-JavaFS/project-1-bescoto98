package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
			List<User> temp = ses.createQuery("FROM User WHERE u_username='" + u + "'" ,User.class).list();
			
			User us = temp.get(0);
			return us;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			return null;
		}
		
		return null;
	}

	@Override
	public boolean addUser(User u) {
		Session ses = HibernateUtil.getSession();
		
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(u);
			tx.commit();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		

		return false;
	}

	@Override
	public List<User> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<User> allUsers = ses.createQuery("From User").list();
		
		return allUsers;
	}


}
