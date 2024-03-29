package dev.rds.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;

@Component
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	Set<Appointment> findByAccount(Account account);
	Set<Appointment> findByEvent(Event event);
	
	//Set<Appointment> findByAccountAndType(Account account, Type type);
	//Set<Appointment> findByEventAndType(Event event, Type type);

	Set<Appointment> findByAccountAndAttending(Account account, boolean attending);
	Set<Appointment> findByAccountAndAdmin(Account account, boolean admin);
	
	Set<Appointment> findByEventAndAttending(Event event, boolean attending);
	Set<Appointment> findByEventAndAdmin(Event event, boolean admin);
	
	Appointment findByAccountAndEvent(Account account, Event event);

}
