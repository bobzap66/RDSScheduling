package dev.rds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Event;
import dev.rds.repositories.EventRepository;


@Component
@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventRepository er;

	@Override
	public Event createEvent(Event event) {
		event = er.save(event);
		
		return event;
	}

	@Override
	public Event getEventById(int id) {
		Event event = er.findById(id).get();
		return event;
	}

	@Override
	public Event updateEvent(Event event) {
		
		event = er.save(event);
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

}
	
	
	

