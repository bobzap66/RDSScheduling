package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;

public interface AppointmentService {
	
	// Create
	Appointment createAppointment(Appointment appointment);
	Appointment createAppointment(Account account, Event event, Type type);
	
	// Read
	Set<Appointment> getAppointmentsByAccount(Account account);
	Set<Appointment> getAppointmentsByEvent(Event event);
		
	Set<Appointment> getAppointmentsByAccountAndType(Account account, Type type);
	Set<Appointment> getAppointmentsByEventAndType(Event event, Type type);
	
	Set<Appointment> getAppointmentsByAccountAndEvent(Account account, Event event);
	
	// Update
	Appointment updateAppointment(Appointment appointment);
	
	// Delete
	boolean deleteAppointment(Appointment appointment);

}
