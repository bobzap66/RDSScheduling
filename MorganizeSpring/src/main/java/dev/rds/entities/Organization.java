package dev.rds.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "organization")
public class Organization 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "o_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "organizations")
	private Set<Tag> tags;
	
	@OneToMany(mappedBy = "organization")
	private Set<Event> events;

	
	@OneToMany(mappedBy = "organization")
	private Set<Membership> memberships;

	
	public Organization() {
		super();
	}


	public Organization(int id, String name, String description, Set<Tag> tags, Set<Event> events,
			Set<Membership> memberships) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.events = events;
		this.memberships = memberships;
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<Membership> memberships) {
		this.memberships = memberships;
	}

	@Override
	public String toString() {
		return "Orginization [id=" + id + ", name=" + name + ", description=" + description + ", tags=" + tags
				+ ", events=" + events + ", memberships=" + memberships + "]";
	}
	
	
	
	
}
