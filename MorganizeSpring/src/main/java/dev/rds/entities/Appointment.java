package dev.rds.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	public static Type ATTENDEE = Type.MEMBER;
	public static Type ADMIN = Type.ADMIN;
	
	@ManyToOne
	@JoinColumn(name = "a_id")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "e_id")
	private Event event;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Type type;

	public Appointment() {
		super();
	}

	public Appointment(Account account, Event event, Type type) {
		super();
		this.account = account;
		this.event = event;
		this.type = type;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
