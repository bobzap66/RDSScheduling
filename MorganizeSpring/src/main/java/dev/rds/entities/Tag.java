package dev.rds.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "tag")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class, 
		property = "id")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int id;
	
	@Column(name = "tag")
	private String tag;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "event_tag",
	joinColumns = {@JoinColumn(name = "t_id")},
	inverseJoinColumns = {@JoinColumn(name = "e_id")})
	@JsonIgnore
	private Set<Event> events;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "organization_tag",
	joinColumns = {@JoinColumn(name = "t_id")},
	inverseJoinColumns = {@JoinColumn(name = "o_id")})
	@JsonIgnore
	private Set<Organization> organizations;

	public Tag() {
		super();
		organizations = new HashSet<Organization>();
		events = new HashSet<Event>();
	}

	public Tag(int id, String tag, Set<Event> events, Set<Organization> organizations) {
		super();
		this.id = id;
		this.tag = tag;
		this.events = events;
		this.organizations = organizations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tag=" + tag + "]";
	}
	
	

}
