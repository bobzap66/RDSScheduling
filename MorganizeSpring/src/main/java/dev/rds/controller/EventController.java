package dev.rds.controller;

import java.util.List;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rds.entities.Account;
import dev.rds.entities.Event;
import dev.rds.entities.Type;
import dev.rds.services.AccountService;
import dev.rds.services.AppointmentService;
import dev.rds.services.EventService;

@RestController
@CrossOrigin
public class EventController {

	@Autowired
	EventService es;
	
	@Autowired
	AccountService as;
	
	@Autowired
	AppointmentService apts;
	
	
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
}
