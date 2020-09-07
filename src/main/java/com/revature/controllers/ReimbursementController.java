package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.models.*;
import com.revature.services.*;

public class ReimbursementController {
	
	private static ReimbursementService rs = new ReimbursementService();
	private static UserService us = new UserService();
	
	private static ObjectMapper om = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(ReimbursementController.class);
	
	public void getReimbursement(HttpServletResponse res, int id) throws IOException{
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		ReimbursementDTO r = rs.findById(id);
		
		if(r == null) {
			res.setStatus(204);
			return;
		}
		
		res.setStatus(200);
		String json = om.writeValueAsString(r);
		res.getWriter().println(json);
	}
	
	public void getAllTickets(HttpServletResponse res) throws IOException{
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		List<ReimbursementDTO> allTickets = rs.findAll();
		
		res.getWriter().println(om.writeValueAsString(allTickets));
		res.setStatus(200);
	}
	
//	public void getByStatus(HttpServletResponse res, String status) throws IOException{
//		
//		//should come in verified that the response is of a correct type for the db
//		
//		List<Reimbursement> specTickets = rs.findByStatus(status);
//		
//		res.getWriter().println(om.writeValueAsString(specTickets));
//		res.setStatus(200);
//	}
	
	public void addTicket(HttpServletRequest req, HttpServletResponse res) throws IOException{
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		ReimbursementDTO rdto = om.readValue(body, ReimbursementDTO.class);
		
		// ticket does not exist yet
		
		Reimbursement r = new Reimbursement();
		
		
		r.setR_amnt(rdto.r_amnt);
		r.setR_submitted(rdto.r_submitted);
		
		r.setR_description(rdto.r_description);
		r.setR_author(us.findById(rdto.r_author));
		
		r.setR_status(rs.findStatusById(rdto.r_status));
		r.setR_type(rs.findType(rdto.r_type));
		
		
		if(rs.addReimbursement(r)) {
			res.setStatus(201);
			res.getWriter().println("Reimbursement Ticket created");
			log.info("Ticket created: " + r);
		}
		else {
			res.setStatus(403);
			log.error("Unable to add ticket: " + r);
		}
	}
	
	public void updateTicket(HttpServletRequest req, HttpServletResponse res) throws IOException{
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		ReimbursementDTO rdto = om.readValue(body, ReimbursementDTO.class);
		
		// ticket already exists
		
		Reimbursement r = new Reimbursement();
		
		r.setR_id(rdto.r_id);
		r.setR_amnt(rdto.r_amnt);
		r.setR_submitted(rdto.r_submitted);
		r.setR_resolved(rdto.r_resolved);	
		r.setR_description(rdto.r_description);
		r.setR_author(us.findById(rdto.r_author));
		r.setR_resolver(us.findById(rdto.r_resolver));
		r.setR_status(rs.findStatusById(rdto.r_status));
		r.setR_type(rs.findType(rdto.r_type));
		
		log.info("Request to update ticket: " + r);
		
		if(rs.updateReimbursement(r)) {
			res.setStatus(202);
			res.getWriter().println("Reimbursement Ticket updated");
			log.info("Ticket successfully updated: " + r);
		}
		else {
			log.error("Unable to update ticket: " + r);
			res.setStatus(304);
		}
	}

}
