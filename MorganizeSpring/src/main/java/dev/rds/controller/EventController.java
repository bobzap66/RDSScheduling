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
	public void unregisterFromEvent(@PathVariable int id, @RequestBody Account account) {
		if(account == null || as.getAccountById(account.getId()) != null) {
			return;
		}
		Event event = es.getEventById(id);
		
		Appointment appt = apts.getAppointmentByAccountAndEvent(account, event);
		if(appt != null) {
			appt.setAttending(false);

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
		Appointment appt = apts.getAppointmentByAccountAndEvent(account, event);
		if(appt != null) {
			appt.setAttending(true);
			apts.updateAppointment(appt);
		}else {
			apts.createAppointment(account, event, true, false);
		}
		event = es.getEventById(id);
		return event;
		
	}
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	@ResponseBody
	public Set<Event> getAllEventsByTag(@RequestParam(required = false) String tag)
	{
		Set<Event> events;
		if(tag != null)
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
	
	@RequestMapping(value = "/events/{e_id}/appointments", method = RequestMethod.GET)
	public Set<Appointment> getAppointmentsByEvent(@PathVariable int e_id){
		Event event = es.getEventById(e_id);
		Set<Appointment> appointments = apts.getAppointmentsByEvent(event);
		return appointments;
	}
	
	@RequestMapping(value = "/events/{e_id}/appointments/{u_id}", method = RequestMethod.DELETE)
	public void unregisterFromEvent(@PathVariable int e_id, @PathVariable int u_id) {
		Event event = es.getEventById(e_id);
		Account account = as.getAccountById(u_id);
		if(event == null || account == null) {
			return;
		}
		
		Appointment appt = apts.getAppointmentByAccountAndEvent(account, event);
		System.out.println(appt);
		if(appt != null) {
			if(appt.isAdmin()) {
				appt.setAttending(false);
				apts.updateAppointment(appt);
			}else {
				apts.deleteAppointment(appt);
			}
			
		}
		return;
	}
	

}
