package dev.rds.services;

import dev.rds.entities.Event;

public interface EventService {
	
	//create event
	Event createEvent(Event event);
	
	//read event
	Event getEventById(int id);
	
	//update event
	Event updateEvent(Event event);
	
	//delete event
	boolean deleteEvent(Event event);

}
