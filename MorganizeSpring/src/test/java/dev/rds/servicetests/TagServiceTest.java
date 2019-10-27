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

import dev.rds.entities.Tag;
import dev.rds.services.TagService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class TagServiceTest {
	
	@Autowired
	TagService ts;

//	@Test
//	@Rollback
//	void createTag() {
//		Tag tag = new Tag();
//		tag.setTag("Dungeons and Dragons");
//		tag = ts.createTag(tag);
//
//	}
	
	@Test
	void getTagById() {
		Tag tag = ts.getTagById(1);
		System.out.println(tag);
	}
	
	@Test
	void getTagByTag() {
		Tag tag = ts.getTagByTag("dungeons and DRAGONS");
		Assert.assertEquals(tag.getId(), 1);
	}

}
