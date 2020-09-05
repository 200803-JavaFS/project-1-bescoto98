package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {
	
	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException{
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			
			while(line != null) {
				sb.append(line);
				line = reader.readLine();
			}
			
			String body = new String(sb);
//			System.out.println(body);
			
			LoginDTO l = om.readValue(body,LoginDTO.class);
			
			if(ls.login(l)) {
				HttpSession ses = req.getSession();
				ses.setAttribute("user", l);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Loggged in!");
			}
			else {
				HttpSession ses = req.getSession(false);
				if(ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Unable to login.");
			}
		}
		
	}

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession ses = req.getSession(false);
		
		if(ses != null) {
			ses.invalidate();
			res.setStatus(200);
		}
		else {
			res.setStatus(400);
			res.getWriter().println("Must be logged in to logout.");
		}
		
	}

}
