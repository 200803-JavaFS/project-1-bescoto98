package com.revature.services;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.hash.Hashing;

import com.revature.dao.*;
import com.revature.models.LoginDTO;
import com.revature.models.User;

public class LoginService {
	
	private static IUserDAO ud = new UserDAO();
	private static final Logger log = LogManager.getLogger(LoginService.class);

	public boolean login(LoginDTO l) {
		
		User temp = ud.findByUsername(l.username);
		
		if(temp != null) {
			String hashedPW = Hashing.sha256().hashString(l.password, StandardCharsets.UTF_8).toString();

			if(hashedPW.equals(temp.getU_password())) {
				log.info("Logged in: " + temp.getU_username());
				return true;
			}
		}
		
		return false;	
	}

}
