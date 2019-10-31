package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
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
import dev.rds.entities.Event;
import dev.rds.entities.Tag;
import dev.rds.services.AccountService;
import dev.rds.services.EventService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class EventServiceTest {
	
	@Autowired
	EventService es;
	
	@Autowired
	AccountService as;

	@Test
	@Rollback
	public void createEventTest() {
		 Event event = new Event(); 
		 event.setName("Downtown Blues");
		 event.setStartdate(1573556400000L);
		 event.setEnddate(1573588800000L);
		 event.setDescription(" Music Festival");
		 event.setMaxattendees(4000);
		 event.setLocation("Downtown Morgantown");
		 Set<Tag> tags = new HashSet<Tag>();
		 Tag tag = new Tag();
		 Tag tag2 = new Tag();
		 tag.setTag("Music");
		 tags.add(tag);
		 tag2.setTag("Festival");
		 tags.add(tag2);
		 event.setTags(tags);
		 Account account = as.getAccountById(1010);
		 Event myEvent = this.es.createEvent(event, account);
		 boolean result = event.getName().equals(myEvent.getName());
		 assertEquals(true, result);
		 
	}
	
	
	@Test
	@Rollback
	public void getEventByIdTest() {
		Event event = this.es.getEventById(2007);
		String name = "Cookout";
		boolean result = name.equals(event.getName());
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void updateEventTest() {
		Event event = this.es.getEventById(2007);
		String name = "Cookout2";
		event.setName(name);
		event = this.es.updateEvent(event);
		event = this.es.getEventById(2007);
		boolean result = name.equals(event.getName());
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	    void deleteEvent() {
		Event event = this.es.getEventById(2007);
		boolean result = this.es.deleteEvent(event);
		assertEquals(true, result);
	}
	

}
