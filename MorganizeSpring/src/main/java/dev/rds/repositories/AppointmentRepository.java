package dev.rds.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

	Set<Appointment> findByAccount(Account account);
	Set<Appointment> findByEvent(Event event);
	
	Set<Appointment> findByAccountAndType(Account account, Type type);
	Set<Appointment> findByEventAndType(Event event, Type type);
}
