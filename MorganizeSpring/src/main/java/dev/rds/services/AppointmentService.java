package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;

public interface AppointmentService {
	
	// Create
	Appointment createAppointment(Appointment appointment);
	Appointment createAppointment(Account account, Event event, boolean attending, boolean admin);
	
	// Read
	Set<Appointment> getAppointmentsByAccount(Account account);
	Set<Appointment> getAppointmentsByEvent(Event event);
		
	Set<Appointment> getAppointmentsByAccountAndType(Account account, Type type);
	Set<Appointment> getAppointmentsByEventAndType(Event event, Type type);
	
	Appointment getAppointmentByAccountAndEvent(Account account, Event event);
	
	Set<Appointment> getAppointmentsByAccountAndEventAndType(Account account, Event event, Type type);
	
	// Update
	Appointment updateAppointment(Appointment appointment);
	
	// Delete
	boolean deleteAppointment(Appointment appointment);

}
