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
	public void createEventTest() {
		 Event event = new Event(); 
		 event.setName("rollbackevent");
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
	public void getEventByIdTest() {
		 
		Event event2 = this.es.getEventById(2046);
		String name = "TestEventName";
		boolean result = name.contentEquals(event2.getName());
	}
	
	@Test
	@Rollback
	public void updateEventTest() {
		 Event event = new Event(); 
		 event.setName("updateEvent1");
		 event.setStartdate(10-10-2018);
		 event.setEnddate(10-11-2018);
		 event.setDescription(" Music Festival");
		 event.setMaxattendees(4000);
		 event.setLocation("Downtown Morgantown");
		 event.setId(2001);
		
		Event event3 = this.es.getEventById(2046);
		if (event3 == event) {
		String name = "TestName";
		event3.setName(name);
		event3 = this.es.updateEvent(event);
		event = this.es.getEventById(2001);
		boolean result = name.equals(event3.getName());
		Assert.assertEquals(true, result);}
	}
	
	@Test
	@Rollback
	    void deleteEvent() {
		Event event4 = this.es.getEventById(2046);
		boolean result = this.es.deleteEvent(event4);
		Assert.assertEquals(true, result);
	}
	

}
