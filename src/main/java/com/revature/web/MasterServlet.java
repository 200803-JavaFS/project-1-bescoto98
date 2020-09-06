package com.revature.web;

import java.io.IOException;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.*;

public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static LoginController lc = new LoginController();
	private static ReimbursementController rc = new ReimbursementController();
	private static UserController uc = new UserController();
	
	public MasterServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		res.setContentType("application/json");
		
		res.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/project1/","");
		
		String[] portions = URI.split("/");
		
		System.out.println(Arrays.toString(portions));
		
		try {
			
			switch(portions[0]) {
				case "login":
					if(req.getMethod().equals("GET")) {
						lc.getUser(req,res);
					}
					else if(req.getMethod().equals("POST")) {
						lc.login(req,res);
					}
					
					break;
				case "logout":
					lc.logout(req,res);
					break;
				case "reimbursement":
					
					if((boolean) req.getSession().getAttribute("loggedin") == false) {
						res.setStatus(403);
						res.getWriter().println("You must log in first.");	
						break;
					}
					
					if(req.getSession(false) != null) {
					
						if(req.getMethod().equals("GET")) { //single ticket requested
							if(portions.length == 2) {
								int id = Integer.parseInt(portions[1]);
								rc.getReimbursement(res, id);
							}
							else if(portions.length == 1) { // all tickets requested
								
								rc.getAllTickets(res);
							}
						}
						else if(req.getMethod().equals("POST")) {
							rc.addTicket(req, res);
						}
						else if(req.getMethod().equals("PUT")) {
							System.out.println("update request");
							rc.updateTicket(req, res);
						}
					}
					else {
						res.setStatus(403);
						res.getWriter().println("You must log in first.");					
					}
					
					break;
				
			}
			
			
			
			
			
			
			
		} catch(NumberFormatException e) {
			res.getWriter().print("Invalid location requested.");
			res.setStatus(400);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		doGet(req,res);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}

}
