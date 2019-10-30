package dev.rds.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.entities.Type;
import dev.rds.repositories.EventRepository;


@Component
@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository er;
	
	@Autowired
	AppointmentService apts;
	
	@Autowired
	MembershipService ms;


	@Override
	public Event getEventById(int id) {
		Event event = er.findById(id).get();
		return event;
	}

	@Override
	public Event updateEvent(Event event) {
		Event actual = er.findById(event.getId()).orElse(null);
		if(actual == null) {
			return null;
		}
		if(event.getName() != null) {
			actual.setName(event.getName());	
		}
		if(event.getDescription() != null) {
			actual.setDescription(event.getDescription());
		}
		if(event.getStartdate() != 0) {
			actual.setStartdate(event.getStartdate());
		}
		if(event.getEnddate() != 0) {
			actual.setEnddate(event.getEnddate());
		}
		if(event.getMaxattendees() != 0) {
			actual.setMaxattendees(event.getMaxattendees());
		}
		if(event.getLocation() != null) {
			actual.setLocation(event.getLocation());
		}
		event = er.save(actual);
		return event;
	}

	@Override
	public boolean deleteEvent(Event event) {
		try {
			er.delete(event);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Set<Event> getEventsByOrganization(Organization organization) {
		Set<Event> events = er.findByOrganization(organization);
		return events;
	}

	@Override
	public Event createEvent(Event event, Account account) {
		event = er.save(event);
		apts.createAppointment(account, event, Type.ADMIN);
		return event;
	}
	
	@Override
	public Event createEvent(Event event) {
		event = er.save(event);
		Organization organization = event.getOrganization();
		Set<Account> admins = ms.getMembershipsByOrganizationAndType(organization, Type.ADMIN);
		for(Account account : admins) {
			apts.createAppointment(account, event, Type.ADMIN);
		}
		event = er.findById(event.getId()).orElse(null);
		
		return event;
	}

	@Override
	public Set<Event> getEventsByTag(Tag tag) 
	{
		Set<Event> eventsByTag = er.findByTags(tag);
		return eventsByTag;
	}

	@Override
	public Set<Event> getAllEvents() 
	{
		Set<Event> events = er.findAll();
		return events;
	}


}
	
	
	

