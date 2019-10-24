package dev.rds.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
public class Membership {
	
	public static Type MEMBER = Type.MEMBER;
	public static Type ADMIN = Type.ADMIN;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@Column(name = "type")
	private Type type;

}
