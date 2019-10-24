package dev.rds.entities;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "event")
public class Event 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "e_id ")
	private int id;
	
	@Column(name="name ")
	private String name;
	
	@Column(name="startdate ")
	private long startdate;
	
	@Column(name= "enddate")
	private long enddate;
	
	@Column(name= "description")
	private String description;
	
	@Column(name= "maxattendees")
	private int maxattendees;
	
	@Column(name= "location")
	private String location;
	
	@ManyToOne
	@JoinColumn(name= "org_id")
	private Organization group;
	
	
	private Set<Account> attendees;
	
	
	private Set<Account> admins;

	
	public Event() {
		super();
	}
	
	public Event(int id, String name, long startdate, long enddate, String description, int maxattendees,
			String location, Organization group, Set<Account> attendees, Set<Account> admins) {
		super();
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.maxattendees = maxattendees;
		this.location = location;
		this.group = group;
		this.attendees = attendees;
		this.admins = admins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStartdate() {
		return startdate;
	}

	public void setStartdate(long startdate) {
		this.startdate = startdate;
	}

	public long getEnddate() {
		return enddate;
	}

	public void setEnddate(long enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxattendees() {
		return maxattendees;
	}

	public void setMaxattendees(int maxattendees) {
		this.maxattendees = maxattendees;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Organization getGroup() {
		return group;
	}

	public void setGroup(Organization group) {
		this.group = group;
	}

	public Set<Account> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<Account> attendees) {
		this.attendees = attendees;
	}

	public Set<Account> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<Account> admins) {
		this.admins = admins;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", description=" + description + ", maxattendees=" + maxattendees + ", location=" + location
				+ ", attendees=" + attendees + ", admins=" + admins + "]";
	}
	
	
	
	
}
