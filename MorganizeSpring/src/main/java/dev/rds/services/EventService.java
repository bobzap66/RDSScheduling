package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Organization;

public interface EventService {
	
	//create event
	Event createEvent(Event event, Account account);
	
	//read event
	Event getEventById(int id);
	
	//update event
	Event updateEvent(Event event);
	
	//delete event
	boolean deleteEvent(Event event);
	
	Set<Event> getEventsByOrganization(Organization organization);

}
