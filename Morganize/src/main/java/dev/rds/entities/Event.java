package dev.rds.entities;

import java.util.Set;

public class Event
{
	private String name;
	private String startdate;
	private String enddate;
	private String description;
	private int maxattendees;
	private String location;
	private Orginization group;
	private Set<User> attendees;
	private Set<User> admins;
	
	
	
	public Event() {
		super();
	}





	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
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


	public Orginization getGroup() {
		return group;
	}


	public void setGroup(Orginization group) {
		this.group = group;
	}


	public Set<User> getAttendees() {
		return attendees;
	}


	public void setAttendees(Set<User> attendees) {
		this.attendees = attendees;
	}


	public Set<User> getAdmins() {
		return admins;
	}


	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}
	
	
	
	
	
}
