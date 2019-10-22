package dev.rds.entities;

import java.util.Set;

public class User 
{
	private int id;
	private String name;
	private String password;
	private String email;
	private Set<Orginization> subscriptions;
	
	
	
	public User() 
	{
		super();
	}



	public User(int id, String name, String password, String email, Set<Orginization> subscriptions) 
	{
		super();
		this.id = id;
		this.name = name;
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



	public Set<Orginization> getSubscriptions() {
		return subscriptions;
	}



	public void setSubscriptions(Set<Orginization> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
	
	
	
}
