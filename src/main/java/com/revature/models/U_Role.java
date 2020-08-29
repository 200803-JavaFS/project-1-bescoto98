package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class U_Role implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ur_id;
	
	@Column(nullable=false)
	private String ur_description;

	public U_Role() {
		super();
	}

	public U_Role(int ur_id, String ur_description) {
		super();
		this.ur_id = ur_id;
		this.ur_description = ur_description;
	}

	public int getUr_id() {
		return ur_id;
	}

	public String getUr_description() {
		return ur_description;
	}

	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}

	public void setUr_description(String ur_description) {
		this.ur_description = ur_description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ur_description == null) ? 0 : ur_description.hashCode());
		result = prime * result + ur_id;
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
		U_Role other = (U_Role) obj;
		if (ur_description == null) {
			if (other.ur_description != null)
				return false;
		} else if (!ur_description.equals(other.ur_description))
			return false;
		if (ur_id != other.ur_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "U_Role [ur_id=" + ur_id + ", ur_description=" + ur_description + "]";
	}

	
	
}
