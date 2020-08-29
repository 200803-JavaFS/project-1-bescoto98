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
		
//		final String URI = req.getRequestURI().replace("/AnimalsDemo/","");
//		
//		String[] portions = URI.split("/");
//		
//		System.out.println(Arrays.toString(portions));
		
	}

}
