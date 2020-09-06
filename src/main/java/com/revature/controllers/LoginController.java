package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.models.LoginDTO;
import com.revature.models.UserDTO;
import com.revature.services.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController {
	
	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();
	private static UserService us = new UserService();

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
			
			LoginDTO l = om.readValue(body,LoginDTO.class);
			
			if(ls.login(l)) {
				HttpSession ses = req.getSession();
				ses.setAttribute("user", l);
				ses.setAttribute("loggedin", true);
				
				// return userdto object
				UserDTO userObj = us.findUser(l.username);
//				res.getWriter().println(om.writeValueAsString(userObj));
				ses.setAttribute("userdto", userObj);
				
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

	public void getUser(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession ses = req.getSession();
		UserDTO temp = (UserDTO) ses.getAttribute("userdto");
		
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		String json = om.writeValueAsString(temp);
		
		res.setStatus(200);
		res.getWriter().println(json);
		
		
	}

}
