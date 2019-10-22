package dev.rds.entities;

import java.util.Set;

public class Orginization 
{
	private int id;
	private String name;
	private String description;
	private Set<String> tags;
	private Set<Event> events;
	private Set<User> members;
	
	
	public Orginization() {
		super();
	}


	public Orginization(int id, String name, String description, Set<String> tags, Set<Event> events,
			Set<User> members) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.events = events;
		this.members = members;
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<String> getTags() {
		return tags;
	}


	public void setTags(Set<String> tags) {
		this.tags = tags;
	}


	public Set<Event> getEvents() {
		return events;
	}


	public void setEvents(Set<Event> events) {
		this.events = events;
	}


	public Set<User> getMembers() {
		return members;
	}


	public void setMembers(Set<User> members) {
		this.members = members;
	}
	
	
	
	
}