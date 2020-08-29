package com.revature.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int u_id;
	
	@Column(nullable=false,unique=true)
	private String u_username;
	
	@Column(nullable=false)
	private String u_password;
	
	@Column(nullable=false)
	private String u_fn;
	
	@Column(nullable=false)
	private String u_ln;
	
	@Column(nullable=false,unique=true)
	private String u_email;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ur_id")
	private U_Role u_user_role;
	
	public User() {
		super();
	}

	public User(String u_username, String u_password, String u_fn, String u_ln, String u_email, U_Role u_user_role) {
		super();
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_fn = u_fn;
		this.u_ln = u_ln;
		this.u_email = u_email;
		this.u_user_role = u_user_role;
	}

	public User(int u_id, String u_username, String u_password, String u_fn, String u_ln, String u_email,
			U_Role u_user_role) {
		super();
		this.u_id = u_id;
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_fn = u_fn;
		this.u_ln = u_ln;
		this.u_email = u_email;
		this.u_user_role = u_user_role;
	}

	public int getU_id() {
		return u_id;
	}

	public String getU_username() {
		return u_username;
	}

	public String getU_password() {
		return u_password;
	}

	public String getU_fn() {
		return u_fn;
	}

	public String getU_ln() {
		return u_ln;
	}

	public String getU_email() {
		return u_email;
	}

	public U_Role getU_user_role() {
		return u_user_role;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public void setU_username(String u_username) {
		this.u_username = u_username;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public void setU_fn(String u_fn) {
		this.u_fn = u_fn;
	}

	public void setU_ln(String u_ln) {
		this.u_ln = u_ln;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public void setU_user_role(U_Role u_user_role) {
		this.u_user_role = u_user_role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((u_email == null) ? 0 : u_email.hashCode());
		result = prime * result + ((u_fn == null) ? 0 : u_fn.hashCode());
		result = prime * result + u_id;
		result = prime * result + ((u_ln == null) ? 0 : u_ln.hashCode());
		result = prime * result + ((u_password == null) ? 0 : u_password.hashCode());
		result = prime * result + ((u_user_role == null) ? 0 : u_user_role.hashCode());
		result = prime * result + ((u_username == null) ? 0 : u_username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (u_email == null) {
			if (other.u_email != null)
				return false;
		} else if (!u_email.equals(other.u_email))
			return false;
		if (u_fn == null) {
			if (other.u_fn != null)
				return false;
		} else if (!u_fn.equals(other.u_fn))
			return false;
		if (u_id != other.u_id)
			return false;
		if (u_ln == null) {
			if (other.u_ln != null)
				return false;
		} else if (!u_ln.equals(other.u_ln))
			return false;
		if (u_password == null) {
			if (other.u_password != null)
				return false;
		} else if (!u_password.equals(other.u_password))
			return false;
		if (u_user_role == null) {
			if (other.u_user_role != null)
				return false;
		} else if (!u_user_role.equals(other.u_user_role))
			return false;
		if (u_username == null) {
			if (other.u_username != null)
				return false;
		} else if (!u_username.equals(other.u_username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_username=" + u_username + ", u_password=" + u_password + ", u_fn=" + u_fn
				+ ", u_ln=" + u_ln + ", u_email=" + u_email + ", u_user_role=" + u_user_role + "]";
	}
	
	
	
	

}
