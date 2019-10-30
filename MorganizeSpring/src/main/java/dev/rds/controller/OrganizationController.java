package dev.rds.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.entities.Type;
import dev.rds.services.AccountService;
import dev.rds.services.EventService;
import dev.rds.services.MembershipService;
import dev.rds.services.OrganizationService;
import dev.rds.services.TagService;

@RestController
@CrossOrigin
public class OrganizationController {
	
	@Autowired
	OrganizationService os;
	@Autowired
	MembershipService ms;
	@Autowired
	EventService es;
	@Autowired
	TagService ts;
	@Autowired
	AccountService as;
	
	@RequestMapping(value = "/organizations", method = RequestMethod.GET)
	public Set<Organization> getOrganizations(@RequestParam String tag)
	{
		Set<Organization> organizations = os.searchOrganizationsByTag(tag);
		return organizations;
	}
	
	
	@RequestMapping(value = "/organizations/{id}", method = RequestMethod.GET)
	public Organization getOrganizationById(@PathVariable int id) {
		Organization organization = os.getOrganizationById(id);
		return organization;
	}
	
	@RequestMapping(value = "/organizations/{id}", method = RequestMethod.POST)
	public Organization joinOrganization(@PathVariable int id, @RequestBody Account account) {
		account = as.getAccountById(account.getId());
		Organization organization = os.getOrganizationById(id);
		ms.createMembership(account, organization, Type.MEMBER);
		return organization;
	}
	
	@RequestMapping(value = "/organizations/{id}/events", method = RequestMethod.GET)
	public Set<Event> getEventsByOrganization(@PathVariable int id) {
		Organization organization = os.getOrganizationById(id);
		Set<Event> events = es.getEventsByOrganization(organization);
		return events;
	}
	
	@RequestMapping(value = "/organizations/{id}/events", method = RequestMethod.POST)
	public Event createEventForOrganization(@PathVariable int id, @RequestBody Event event) {
		Organization organization = os.getOrganizationById(id);
		event.setOrganization(organization);
		event = es.createEvent(event);
		return event;
	}
	
	@RequestMapping(value = "/users/{u_id}/organizations/{o_id}", method = RequestMethod.PUT)
	public Organization updateOrganization(@PathVariable int u_id, @PathVariable int o_id, @RequestBody Organization organization) {
		Account account = as.getAccountById(u_id);
		if(account == null) {
			return null;
		}
		Set<Account> accounts = ms.getMembershipsByOrganizationAndType(organization, Type.ADMIN);
		if(accounts.contains(account)) {
			organization = os.updateOrganization(organization);
			return organization;
		}
		
		return null;
		
	}
	
	@RequestMapping(value = "/users/{u_id}/organizations/{o_id}", method = RequestMethod.POST)
	public Organization promoteToAdmin(@PathVariable int u_id, @PathVariable int o_id, @RequestBody Account account) {
		Account admin = as.getAccountById(u_id);
		Organization organization = os.getOrganizationById(o_id);
		Membership oAdmin = ms.getMembershipByOrganizationAndAccount(organization, admin);
		if((oAdmin == null)||(oAdmin.getType() == Type.ADMIN)) {
			return null;
		}
		Membership membership = ms.getMembershipByOrganizationAndAccount(organization, account);
		if(membership == null) {
			return null;
		}
		membership.setType(Type.ADMIN);
		ms.updateMembership(membership);
		Organization rv = os.getOrganizationById(o_id);
		return rv;
	}
	
	
	@RequestMapping(value = "/users/{u_id}/organizations/{o_id}", method = RequestMethod.DELETE)
	public boolean promoteToAdmin(@PathVariable int u_id, @PathVariable int o_id) {
		Account admin = as.getAccountById(u_id);
		Organization organization = os.getOrganizationById(o_id);
		Membership oAdmin = ms.getMembershipByOrganizationAndAccount(organization, admin);
		if((oAdmin == null)||(oAdmin.getType() == Type.ADMIN)) {
			return false;
		}
		
		return os.deleteOrganization(organization);
	}
	
}
