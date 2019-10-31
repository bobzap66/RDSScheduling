package dev.rds.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;
import dev.rds.repositories.AppointmentRepository;

@Component
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired 
	AppointmentRepository ar;
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		appointment = ar.save(appointment);
		return appointment;
	}

	@Override
	public Appointment createAppointment(Account account, Event event, Type type) {
		Appointment appointment = new Appointment();
		appointment.setAccount(account);
		appointment.setEvent(event);
		appointment.setType(type);
		appointment = ar.save(appointment);
		return appointment;
	}

	@Override
	public Set<Appointment> getAppointmentsByAccount(Account account) {
		Set<Appointment> appointments = ar.findByAccount(account);
		return appointments;
	}

	@Override
	public Set<Appointment> getAppointmentsByEvent(Event event) {
		Set<Appointment> appointments = ar.findByEvent(event);
		return appointments;
	}

	@Override
	public Set<Appointment> getAppointmentsByAccountAndType(Account account, Type type) {
		Set<Appointment> appointments = ar.findByAccountAndType(account, type);
		return appointments;
	}

	@Override
	public Set<Appointment> getAppointmentsByEventAndType(Event event, Type type) {
		Set<Appointment> appointments = ar.findByEventAndType(event, type);
		return appointments;
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {
		appointment = ar.save(appointment);
		return appointment;
	}

	@Override
	public boolean deleteAppointment(Appointment appointment) {
		try {
			ar.delete(appointment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Set<Appointment> getAppointmentsByAccountAndEvent(Account account, Event event){
		Set<Appointment> appointments = ar.findByAccountAndEvent(account, event);
		return appointments;
	}

}
