package dev.rds.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name= "a_id ")
	private int id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name= "username  ")
	private String username;
	
	@Column(name= "password  ")
	private String password;
	
	@Column(name= "email")
	private String email;
	
	private Set<Organization> subscriptions;
	
	public Account() {
		super();
	}

	public Account(int id, String name, String username, String password, String email, Set<Organization> subscriptions) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.subscriptions = subscriptions;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Organization> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Organization> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + "]";
	}
	
	
	
}
