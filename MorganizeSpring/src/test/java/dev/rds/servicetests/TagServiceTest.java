package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

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
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.services.EventService;
import dev.rds.services.OrganizationService;
import dev.rds.services.TagService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class TagServiceTest {
	
	@Autowired
	TagService ts;
	
	@Autowired 
	EventService es;
	
	@Autowired
	OrganizationService os;

	@Test
	@Rollback
	void createTag() {
		Tag tag = new Tag();
		tag.setTag("Hopscotch");
		tag = ts.createTag(tag);
		Assert.assertTrue("Hopscotch".equals(tag.getTag()));
	}
	
	@Test
	void getTagById() {
		Tag tag = ts.getTagById(1);
		Assert.assertEquals(tag.getTag(), "Dungeons and Dragons");
	}
	
	@Test
	void getTagByTag() {
		Tag tag = ts.getTagByTag("dungeons and DRAGONS");
		Assert.assertEquals(tag.getId(), 1);
	}
	
	@Test
	void getTagsByEvent() {
		Event event = es.getEventById(2012);
		Set<Tag> tags = ts.getTagsByEvent(event);
		Assert.assertEquals(tags.size(), 1);
	}
	
	@Test
	void getTagsByOrganization() {
		Organization organization = os.getOrganizationById(3000);
		Set<Tag> tags = ts.getTagsByOrganization(organization);
		Assert.assertEquals(tags.size(), 1);
	}

}
