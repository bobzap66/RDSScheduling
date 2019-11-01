package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import dev.rds.entities.Account;
import dev.rds.entities.Appointment;
import dev.rds.entities.Event;
import dev.rds.entities.Type;
import dev.rds.services.AccountService;
import dev.rds.services.AppointmentService;
import dev.rds.services.EventService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class AppointmentServiceTest {

	@Autowired
	AppointmentService apptService;
	
	@Autowired
	AccountService as;
	
	@Autowired
	EventService es;
	
	@Test
	@Rollback
	void createAppointment() {
		Account a = as.getAccountById(1012);
		Event e = es.getEventById(2007);
		Appointment appt = apptService.createAppointment(a, e, true, false);
		assertEquals(a.getId(), appt.getAccount().getId());
	}
	
	@Test
	void getAppointmentsByAccount() {
		Account account = as.getAccountById(1012);
		Set<Appointment> appts = apptService.getAppointmentsByAccount(account);
		assertTrue(appts.size() > 0);
	}
	
	@Test
	void getAppointmentsByEvent() {
		Event event = es.getEventById(2012);
		Set<Appointment> appts = apptService.getAppointmentsByEvent(event);
		assertTrue(appts.size() > 0);
	}
	
	@Test
	void getAppointmentsByAccountAndType() {
		Account account = as.getAccountById(1012);
		Set<Appointment> appts = apptService.getAppointmentsByAccountAndType(account, Type.MEMBER);
		assertTrue(appts.size() > 0);
	}
	
	@Test
	void getAppointmentsByEventAndType() {
		
	}

}
