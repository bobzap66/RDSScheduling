package dev.rds.services;

import java.util.HashSet;
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
	
	@Autowired
	TagService ts;


	@Override
	public Event getEventById(int id) {
		Event event = er.findById(id).orElse(null);
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
		Set<Tag> tags = event.getTags();
		Set<Tag> newTags = new HashSet<Tag>();
		if(tags != null) {
			for(Tag tag : tags) {
				//If the tag doesn't doesn't exist in the database, 
				//remove the tag from the set of tags attached to the event
				// and add the tag returned by the database
				if(ts.getTagByTag(tag.getTag()) == null) {
					Tag temp = ts.createTag(tag);
					newTags.add(temp);
					temp.getEvents().add(actual);
				}
				else
				{
					tag = ts.getTagByTag(tag.getTag());
					newTags.add(tag);
					tag.getEvents().add(actual);
				}
			}
			actual.setTags(newTags);
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
		
		Set<Tag> tags = event.getTags();
		Set<Tag> newTags = new HashSet<Tag>();
		if(tags != null) {
			for(Tag tag : tags) {
				//If the tag doesn't doesn't exist in the database, 
				//remove the tag from the set of tags attached to the event
				// and add the tag returned by the database
				if(ts.getTagByTag(tag.getTag()) == null) {
					tag = ts.createTag(tag);
					newTags.add(tag);
					tag.getEvents().add(event);
				} else {
					tag = ts.getTagByTag(tag.getTag());
					newTags.add(tag);
					tag.getEvents().add(event);
				}
			}
			event.setTags(newTags);
		}
		event = er.save(event);

		apts.createAppointment(account, event, false, true);

		return event;
	}
	
	@Override
	public Event createEvent(Event event) {
		Set<Tag> tags = event.getTags();
		Set<Tag> newTags = new HashSet<Tag>();
		if(tags != null) {
			for(Tag tag : tags) {
				//If the tag doesn't doesn't exist in the database, 
				//remove the tag from the set of tags attached to the event
				// and add the tag returned by the database
				if(ts.getTagByTag(tag.getTag()) == null) {
					tag = ts.createTag(tag);
					tags.remove(tag);
					newTags.add(tag);
				}
			}
			event.setTags(newTags);
		}
		event = er.save(event);
		Organization organization = event.getOrganization();
		Set<Account> admins = ms.getMembershipsByOrganizationAndType(organization, Type.ADMIN);
		for(Account account : admins) {
			apts.createAppointment(account, event, false, true);
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
	
	
	

