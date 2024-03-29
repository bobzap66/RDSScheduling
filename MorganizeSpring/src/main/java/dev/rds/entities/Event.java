package dev.rds.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name= "event")
public class Event
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "e_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="startdate")
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
	private Organization organization;
	
	@OneToMany(mappedBy = "event")
	@JsonIgnore
	private Set<Appointment> appointments;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@ManyToMany(mappedBy = "events")
	private Set<Tag> tags;

	public Event() {
		super();
	}

	public Event(int id, String name, long startdate, long enddate, String description, int maxattendees,
			String location, Organization organization, Set<Appointment> appointments) {
		super();
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.maxattendees = maxattendees;
		this.location = location;
		this.organization = organization;
		this.appointments = appointments;
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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", description=" + description + ", maxattendees=" + maxattendees + ", location=" + location
				+ ", organization=" + organization +  "]";
	}

}
