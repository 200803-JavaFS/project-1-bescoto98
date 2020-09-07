package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int s_id;
	@Column(nullable=false)
	private String s_description;
	
	public Status() {
		super();
	}

	public Status(int s_id, String s_description) {
		super();
		this.s_id = s_id;
		this.s_description = s_description;
	}

	public int getS_id() {
		return s_id;
	}

	public String getS_description() {
		return s_description;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public void setS_description(String s_description) {
		this.s_description = s_description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((s_description == null) ? 0 : s_description.hashCode());
		result = prime * result + s_id;
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
		Status other = (Status) obj;
		if (s_description == null) {
			if (other.s_description != null)
				return false;
		} else if (!s_description.equals(other.s_description))
			return false;
		if (s_id != other.s_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [s_id=" + s_id + ", s_description=" + s_description + "]";
	}
	
	
	
	
}
