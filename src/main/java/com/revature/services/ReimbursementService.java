package com.revature.services;

import com.revature.dao.*;
import com.revature.models.*;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {
	
	private static IReimbursementDAO r = new ReimbursementDAO();
	
	public List<ReimbursementDTO> findAll(){
//		return r.findAll();
		
		List<Reimbursement> allTickets = r.findAll();
		
		List<ReimbursementDTO> sanitizedTickets = new ArrayList<>();
		
		for(Reimbursement temp : allTickets) {
			
			ReimbursementDTO bus = new ReimbursementDTO();
			
			bus.r_id = temp.getR_id();
			bus.r_amnt = temp.getR_amnt();
			bus.r_submitted = temp.getR_submitted();
			if(temp.getR_resolved() != null) {
				bus.r_resolved = temp.getR_resolved();
			}
			bus.r_description = temp.getR_description();
			bus.r_author = temp.getR_author().getU_id();
			if(temp.getR_resolver() != null) {
				bus.r_resolver = temp.getR_resolver().getU_id();
			}
			bus.r_status = temp.getR_status().getS_id();
			bus.r_type = temp.getR_type().getRt_id();
			sanitizedTickets.add(bus);
			
			
		}
		
		return sanitizedTickets;
		
		
	}
	
	public ReimbursementDTO findById(int id) {
		Reimbursement temp = r.findById(id);
		
		ReimbursementDTO ticket = new ReimbursementDTO();
		
		ticket.r_id = temp.getR_id();
		ticket.r_amnt = temp.getR_amnt();
		ticket.r_submitted = temp.getR_submitted();
		if(temp.getR_resolved() != null) {
			ticket.r_resolved = temp.getR_resolved();
		}
		ticket.r_description = temp.getR_description();
		ticket.r_author = temp.getR_author().getU_id();
		if(temp.getR_resolver() != null) {
			ticket.r_resolver = temp.getR_resolver().getU_id();
		}
		ticket.r_status = temp.getR_status().getS_id();
		ticket.r_type = temp.getR_type().getRt_id();
		
		return ticket;
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
