package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class R_Type implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rt_id;
	
	@Column(nullable=false)
	private String rt_type;

	public R_Type() {
		super();
	}

	public R_Type(int rt_id, String rt_type) {
		super();
		this.rt_id = rt_id;
		this.rt_type = rt_type;
	}

	public int getRt_id() {
		return rt_id;
	}

	public String getRt_type() {
		return rt_type;
	}

	public void setRt_id(int rt_id) {
		this.rt_id = rt_id;
	}

	public void setRt_type(String rt_type) {
		this.rt_type = rt_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rt_id;
		result = prime * result + ((rt_type == null) ? 0 : rt_type.hashCode());
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
		R_Type other = (R_Type) obj;
		if (rt_id != other.rt_id)
			return false;
		if (rt_type == null) {
			if (other.rt_type != null)
				return false;
		} else if (!rt_type.equals(other.rt_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "R_Type [rt_id=" + rt_id + ", rt_type=" + rt_type + "]";
	}
	
	
}
