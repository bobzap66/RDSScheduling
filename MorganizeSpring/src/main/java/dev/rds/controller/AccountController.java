package dev.rds.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.services.AccountService;
import dev.rds.services.AppointmentService;
import dev.rds.services.EventService;
import dev.rds.services.MembershipService;
import dev.rds.services.OrganizationService;

@RestController
@CrossOrigin
public class AccountController 
{
	@Autowired
	AccountService as;
	
	@Autowired
	EventService es;
	
	@Autowired
	OrganizationService os;
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	AppointmentService apts;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Account login(@RequestBody Account temp)
	{	
		temp = as.login(temp);	
		return temp;
		
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public Account createAccount(@RequestBody Account account) 
	{
		account = as.createAccount(account);
		return account;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseBody
	public Account updateAccount(@RequestBody Account account) {
		account = as.updateAccount(account);
		return account;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAccount(Account account) {
		as.deleteAccount(account);
		
	}
	
	@RequestMapping(value = "/users/{id}/events", method = RequestMethod.POST)
	@ResponseBody
	public Event createEvent(@PathVariable int id, @RequestBody Event event) {
		Account account = as.getAccountById(id);
		if(account == null) {
			return null;
		}
		Event actual = es.createEvent(event,  account);
		return actual;
		
	}
	
	@RequestMapping(value = "/users/{id}/organizations", method = RequestMethod.POST)
	public Organization createOrganization(@PathVariable int id, @RequestBody Organization organization){
		Account account = as.getAccountById(id);
		if(account == null) {
			return null;
		}
		organization = os.createOrganization(organization, account);
		return organization;
	}
	
	@RequestMapping(value = "/users/{id}/organizations", method = RequestMethod.GET)
	public Set<Membership> getMembershipsByAccount(@PathVariable int id){
		Account account = as.getAccountById(id);
		if(account == null) {
			return null;
		}
		Set<Membership> memberships = ms.getMembershipsByAccount(account);
		return memberships;
	}
	
	@RequestMapping(value = "/users/{id}/events", method = RequestMethod.GET)
	public Set<Appointment> getAppointmentsByAccount(@PathVariable int id){
		Account account = as.getAccountById(id);
		if(account == null) {
			return null;
		}
		Set<Appointment> appointments = apts.getAppointmentsByAccount(account);
		return appointments;
	}
	
}
