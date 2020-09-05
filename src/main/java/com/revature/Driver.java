//package com.revature;
//
//import java.util.List;
//
//import com.revature.dao.*;
//import com.revature.models.*;
//
//public class Driver {
//	
//	private static IReimbursementDAO rd = new ReimbursementDAO();
//	private static IUserDAO ud = new UserDAO();
//
//	public static void main(String[] args) {
//
////		Reimbursement r = new Reimbursement();
////		r.setR_amnt(123.33);
////		r.setR_description("went to texas");
////		r.setR_submitted("07-03-2016");
////		r.setR_author(ud.findById(2));
////		r.setR_status(rd.findStatus(1));
////		r.setR_type(rd.findReimbType(2));
////		
////		System.out.println(rd.addReimbursement(r));
//		
////		Reimbursement r = rd.findById(3);
////		r.setR_resolver(ud.findById(5));
////		r.setR_resolved("10-15-2016");
////		System.out.println(rd.updateReimbursement(r));
////		
//		
////		User u = ud.findByUsername("bates32");
////		System.out.println(u);
//		
////		User temp = new User();
////		
////		temp.setU_email("coopster@gmail.com");
////		temp.setU_fn("Mabelle");
////		temp.setU_ln("Cooper");
////		temp.setU_password("4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2");
////		temp.setU_username("mabelle33");
////		temp.setU_user_role(ud.findUserRole(0));
////		
////		ud.addUser(temp);
//
////		System.out.println(ud.findAll());
//		
//		System.out.println(rd.findById(3));
//	}
//
//}
