package dev.rds.entities;

import java.util.Set;

public class Organization 
{
	private int id;
	private String name;
	private String description;
	private Set<String> tags;
	private Set<Event> events;
	private Set<Account> members;
	
	public Organization() {
		super();
	}

	public Organization(int id, String name, String description, Set<String> tags, Set<Event> events,
			Set<Account> members) {
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

	public Set<Account> getMembers() {
		return members;
	}

	public void setMembers(Set<Account> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Orginization [id=" + id + ", name=" + name + ", description=" + description + ", tags=" + tags
				+ ", events=" + events + ", members=" + members + "]";
	}
	
	
	
	
}
