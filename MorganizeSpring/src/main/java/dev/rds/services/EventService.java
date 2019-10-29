package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

public interface EventService {
	
	//create event
	Event createEvent(Event event, Account account);
	Event createEvent(Event event);
	
	//read event
	Event getEventById(int id);
	
	//update event
	Event updateEvent(Event event);
	
	//delete event
	boolean deleteEvent(Event event);
	
	Set<Event> getEventsByTag(Tag tag);
	Set<Event> getEventsByOrganization(Organization organization);

}
