package com.revature.dao;

import java.util.List;

import com.revature.models.*;

public interface IReimbursementDAO {
	
	public List<Reimbursement> findAll();
	public Reimbursement findById(int id);
	public boolean updateReimbursement(Reimbursement r);
	public boolean addReimbursement(Reimbursement r);
	public Status findStatus(int id);
	public R_Type findReimbType(int id);
	

}
