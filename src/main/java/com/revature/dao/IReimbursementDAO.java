package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAll();
	public Reimbursement findById(int id);
	public List<Reimbursement> findByStatus(String s);
	public boolean updateReimbursement(Reimbursement r);
	public boolean addReimbursement(Reimbursement r);

}
