package dev.rds.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Id;

@Entity
@Table(name = "membership")
public class Membership {
	
	public static Type MEMBER = Type.MEMBER;
	public static Type ADMIN = Type.ADMIN;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mem_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "a_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "o_id")
	private Organization organization;
	
	@Column(name = "type")
	private Type type;

	public Membership() {
		super();
	}

	public Membership(int id, Account account, Organization organization, Type type) {
		super();
		this.id = id;
		this.account = account;
		this.organization = organization;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	

}
