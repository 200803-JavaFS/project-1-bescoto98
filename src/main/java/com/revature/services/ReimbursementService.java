package com.revature.services;

import com.revature.dao.*;
import com.revature.models.*;

import java.util.List;

public class ReimbursementService {
	
	private static IReimbursementDAO r = new ReimbursementDAO();
	
	public List<Reimbursement> findAll(){
		return r.findAll();
	}
	
	public Reimbursement findById(int id) {
		return r.findById(id);
	}
	
//	public List<Reimbursement> findByStatus(String s){
//		return r.findByStatus(s);
//	}
	
	public boolean addReimbursement(Reimbursement ticket) {
		return r.addReimbursement(ticket);
	}
	
	public boolean updateReimbursement(Reimbursement ticket) {
		return r.updateReimbursement(ticket);
	}
	
	public Status findStatusById(int id) {
		return r.findStatus(id);
	}
	
	public R_Type findType(int id) {
		return r.findReimbType(id);
	}
}
