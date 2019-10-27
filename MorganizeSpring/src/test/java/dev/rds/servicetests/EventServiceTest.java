package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
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


import dev.rds.entities.Event;
import dev.rds.services.EventService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class EventServiceTest {
	
	@Autowired
	EventService es;

	@Test
	@Rollback
	//@Commit
	public void createEventTest() {
		 Event event = new Event(); 
		 event.setName("testEvent1");
		 event.setStartdate(10-10-2018);
		 event.setEnddate(10-11-2018);
		 event.setDescription(" Music Festival");
		 event.setMaxattendees(4000);
		 event.setLocation("Downtown Morgantown");
		 Event myEvent = this.es.createEvent(event);
		 boolean result = event.getName().equals(myEvent.getName());
		 Assert.assertEquals(true, result);
		 
	}
	
	
	@Test
	@Rollback
	public void getEventByIdTest() {
		Event event = this.es.getEventById(2001);
		String name = "TestEventName";
		boolean result = name.contentEquals(event.getName());
		Assert.assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void updateEventTest() {
		Event event = this.es.getEventById(2001);
		String name = "TestName";
		event.setName(name);
		event = this.es.updateEvent(event);
		event = this.es.getEventById(2001);
		boolean result = name.equals(event.getName());
		Assert.assertEquals(true, result);
	}
	
	@Test
	@Rollback
	    void deleteEvent() {
		Event event = this.es.getEventById(2001);
		boolean result = this.es.deleteEvent(event);
		Assert.assertEquals(true, result);
	}
	

}