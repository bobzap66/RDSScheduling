package dev.rds.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "appointment")
public class Appointment {

	public static Type ATTENDEE = Type.MEMBER;
	public static Type ADMIN = Type.ADMIN;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appt_id")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "a_id")
	@JsonIgnore
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_id")
	@JsonIgnore
	private Event event;
	
	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private Type type;

	public Appointment() {
		super();
	}

	public Appointment(int id, Account account, Event event, Type type) {
		super();
		this.id = id;
		this.account = account;
		this.event = event;
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
