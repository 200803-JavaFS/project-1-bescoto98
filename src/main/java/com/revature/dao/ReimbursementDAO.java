package com.revature.dao;

import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.revature.util.HibernateUtil;

import com.revature.models.Reimbursement;

public class ReimbursementDAO implements IReimbursementDAO {
	
	public ReimbursementDAO() {
		super();
	}

	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> allTickets = ses.createQuery("From Reimbursement").list();
		
		return allTickets;
	}

	@Override
	public Reimbursement findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		try {
			Reimbursement r = ses.get(Reimbursement.class, id);
			return r;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> findByStatus(String s) {
		Session ses = HibernateUtil.getSession();
		
		try {
			List<Reimbursement> tickets = ses.createQuery("FROM Reimbursement WHERE r_status ="+s,Reimbursement.class).list();
		
		return tickets;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public boolean updateReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
	
		try {
			ses.merge(r);
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addReimbursement(Reimbursement r) {
		Session ses = HibernateUtil.getSession();
		
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(r);
			tx.commit();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
		}
		

		return false;
	}

}
