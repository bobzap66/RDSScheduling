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
	public Appointment createAppointment(Account account, Event event, boolean attending, boolean admin) {
		Appointment appointment = new Appointment();
		appointment.setAccount(account);
		appointment.setEvent(event);
		appointment.setAttending(attending);
		appointment.setAdmin(admin);
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
		if(type.equals(Type.MEMBER)) {
			Set<Appointment> appointments = ar.findByAccountAndAttending(account, true);
			return appointments;
		}else if(type.equals(Type.ADMIN)) {
			Set<Appointment> appointments = ar.findByAccountAndAdmin(account, true);
			return appointments;
		}
		return null;
		
	}

	@Override
	public Set<Appointment> getAppointmentsByEventAndType(Event event, Type type) {
		if(type.equals(Type.MEMBER)) {
			Set<Appointment> appointments = ar.findByEventAndAttending(event, true);
			return appointments;
		}else if(type.equals(Type.ADMIN)) {
			Set<Appointment> appointments = ar.findByEventAndAdmin(event, true);
			return appointments;
		}
		return null;
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
	
	@Override
	public Appointment getAppointmentByAccountAndEvent(Account account, Event event){
		Appointment appointment = ar.findByAccountAndEvent(account, event);
		return appointment;

	}
<<<<<<< HEAD
	
=======
>>>>>>> branch 'dev' of https://github.com/bobzap66/RDSScheduling.git


}
