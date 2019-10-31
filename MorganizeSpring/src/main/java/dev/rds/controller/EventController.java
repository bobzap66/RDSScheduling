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
import dev.rds.entities.Appointment;
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
	public void deleteEvent(@PathVariable int id, @RequestBody Account account) {
		if(account == null || as.getAccountById(account.getId()) != null) {
			return;
		}
		Event event = es.getEventById(id);
		Set<Appointment> appts = apts.getAppointmentsByAccountAndEvent(account, event);
		if(appts == null || appts.size() < 1) {
			appts.forEach((appt) -> apts.deleteAppointment(appt));
		}
		return;
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
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public Set<Event> getAllEventsByTag(@RequestParam(required = false) String tag)
	{
		Set<Event> events;
		if(tag != null && tag != "")
		{
			Tag tag2 = ts.getTagByTag(tag);
			events = es.getEventsByTag(tag2);
		}
		else
		{
			 events = es.getAllEvents();
		}
		return events;
	}
	
	@RequestMapping(value = "/users/{u_id}/events/{e_id}", method = RequestMethod.PUT)
	@ResponseBody
	public Event updateEvent(@PathVariable int u_id, @PathVariable int e_id, @RequestBody Event event) {
		Account account = as.getAccountById(u_id);
		if(account == null) {
			return null;
		}
		Set<Appointment> appointments = apts.getAppointmentsByEventAndType(event, Type.ADMIN);
		for(Appointment appointment : appointments ) {
			if(appointment.getAccount().getId() == account.getId()) {
				event = es.updateEvent(event);
				return event;
			}
			
		}
		
		return null;
		
	}
	
	@RequestMapping(value = "/users/{u_id}/events/{e_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteEvent(@PathVariable int u_id, @PathVariable int e_id) {
		Account account = as.getAccountById(u_id);
		if(account == null) {
			return false;
		}
		Event event = es.getEventById(e_id);
		if(event == null) {
			return false;
		}
		Set<Appointment> appointments = apts.getAppointmentsByEventAndType(event, Type.ADMIN);
		for(Appointment appointment : appointments ) {
			if(appointment.getAccount().getId() == account.getId()) {
		
				return es.deleteEvent(event);
			}
			
		}
		
		return false;
		
	}

}
