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
public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int r_id;
	
	@Column(nullable=false)
	private double r_amnt;
	
	@Column(nullable=false)
	private String r_submitted;
	
	private String r_resolved;
	private String r_description;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="r_author")
	private User r_author;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="r_resolver")
	private User r_resolver;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="s_id")
	private Status r_status;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="rt_id")
	private R_Type r_type;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(double r_amnt, String r_submitted, String r_resolved, String r_description, User r_author,
			User r_resolver, Status r_status, R_Type r_type) {
		super();
		this.r_amnt = r_amnt;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.r_description = r_description;
		this.r_author = r_author;
		this.r_resolver = r_resolver;
		this.r_status = r_status;
		this.r_type = r_type;
	}

	public Reimbursement(int r_id, double r_amnt, String r_submitted, String r_resolved, String r_description,
			User r_author, User r_resolver, Status r_status, R_Type r_type) {
		super();
		this.r_id = r_id;
		this.r_amnt = r_amnt;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.r_description = r_description;
		this.r_author = r_author;
		this.r_resolver = r_resolver;
		this.r_status = r_status;
		this.r_type = r_type;
	}

	public int getR_id() {
		return r_id;
	}

	public double getR_amnt() {
		return r_amnt;
	}

	public String getR_submitted() {
		return r_submitted;
	}

	public String getR_resolved() {
		return r_resolved;
	}

	public String getR_description() {
		return r_description;
	}

	public User getR_author() {
		return r_author;
	}

	public User getR_resolver() {
		return r_resolver;
	}

	public Status getR_status() {
		return r_status;
	}

	public R_Type getR_type() {
		return r_type;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public void setR_amnt(double r_amnt) {
		this.r_amnt = r_amnt;
	}

	public void setR_submitted(String r_submitted) {
		this.r_submitted = r_submitted;
	}

	public void setR_resolved(String r_resolved) {
		this.r_resolved = r_resolved;
	}

	public void setR_description(String r_description) {
		this.r_description = r_description;
	}

	public void setR_author(User r_author) {
		this.r_author = r_author;
	}

	public void setR_resolver(User r_resolver) {
		this.r_resolver = r_resolver;
	}

	public void setR_status(Status r_status) {
		this.r_status = r_status;
	}

	public void setR_type(R_Type r_type) {
		this.r_type = r_type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(r_amnt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((r_author == null) ? 0 : r_author.hashCode());
		result = prime * result + ((r_description == null) ? 0 : r_description.hashCode());
		result = prime * result + r_id;
		result = prime * result + ((r_resolved == null) ? 0 : r_resolved.hashCode());
		result = prime * result + ((r_resolver == null) ? 0 : r_resolver.hashCode());
		result = prime * result + ((r_status == null) ? 0 : r_status.hashCode());
		result = prime * result + ((r_submitted == null) ? 0 : r_submitted.hashCode());
		result = prime * result + ((r_type == null) ? 0 : r_type.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(r_amnt) != Double.doubleToLongBits(other.r_amnt))
			return false;
		if (r_author == null) {
			if (other.r_author != null)
				return false;
		} else if (!r_author.equals(other.r_author))
			return false;
		if (r_description == null) {
			if (other.r_description != null)
				return false;
		} else if (!r_description.equals(other.r_description))
			return false;
		if (r_id != other.r_id)
			return false;
		if (r_resolved == null) {
			if (other.r_resolved != null)
				return false;
		} else if (!r_resolved.equals(other.r_resolved))
			return false;
		if (r_resolver == null) {
			if (other.r_resolver != null)
				return false;
		} else if (!r_resolver.equals(other.r_resolver))
			return false;
		if (r_status == null) {
			if (other.r_status != null)
				return false;
		} else if (!r_status.equals(other.r_status))
			return false;
		if (r_submitted == null) {
			if (other.r_submitted != null)
				return false;
		} else if (!r_submitted.equals(other.r_submitted))
			return false;
		if (r_type == null) {
			if (other.r_type != null)
				return false;
		} else if (!r_type.equals(other.r_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [r_id=" + r_id + ", r_amnt=" + r_amnt + ", r_submitted=" + r_submitted + ", r_resolved="
				+ r_resolved + ", r_description=" + r_description + ", r_author=" + r_author + ", r_resolver="
				+ r_resolver + ", r_status=" + r_status + ", r_type=" + r_type + "]";
	}
	
	
}
