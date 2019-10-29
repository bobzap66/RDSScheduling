package dev.rds.controller;

import java.util.List;
import java.util.Set;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Tag;
import dev.rds.entities.Type;
import dev.rds.services.AccountService;
import dev.rds.services.AppointmentService;
import dev.rds.services.EventService;
import dev.rds.services.TagService;

@RestController
@CrossOrigin
public class EventController {

	@Autowired
	EventService es;
	
	@Autowired
	AccountService as;
	
	@Autowired
	AppointmentService apts;
	
	@Autowired
	TagService ts;
	
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvent(@RequestBody Event event, @PathVariable int id) {
		event.setId(id);
		event = es.updateEvent(event);
		return event;
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteEvent(@PathVariable int id) {
		Event event = es.getEventById(id);
		es.deleteEvent(event);
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getEventById(@PathVariable int id) {
		Event event = es.getEventById(id);
		return event;
	}
	
	@RequestMapping(value = "/events/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Event registerForEvent(@PathVariable int id, @RequestBody Account account) {
		account = as.getAccountByUsername(account.getUsername());
		Event event = es.getEventById(id);
		apts.createAppointment(account, event, Type.MEMBER);
		event = es.getEventById(id);
		return event;
		
	}
	
	@RequestMapping(value = "/allEvents/{tag}", method = RequestMethod.POST)
	@ResponseBody
	public Set<Event> getAllEventsByTag(@PathVariable String tag)
	{
		Tag tag2 = ts.getTagByTag(tag);
		Set<Event> events = es.getEventsByTag(tag2);
		return events;
	}
}
