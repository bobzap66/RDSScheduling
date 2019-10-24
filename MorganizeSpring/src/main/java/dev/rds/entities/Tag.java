package dev.rds.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int id;
	
	@Column(name = "tag")
	private String tag;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "event_tag",
	joinColumns = {@JoinColumn(name = "t_id")},
	inverseJoinColumns = {@JoinColumn(name = "e_id")})
	private Set<Event> events;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "organization_tag",
	joinColumns = {@JoinColumn(name = "t_id")},
	inverseJoinColumns = {@JoinColumn(name = "o_id")})
	private Set<Organization> organizations;
	
	

}
