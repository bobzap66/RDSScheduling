package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import dev.rds.entities.Organization;
import dev.rds.services.OrganizationService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class OrganizationServiceTest {

	@Autowired
	OrganizationService os;
	
	@Test
	@Commit
	public void createOrganizationTest() {
		Organization organization = new Organization();
		organization.setName("D&D Winners");
		organization.setDescription("A really awesome D&D group full of winners");
		Organization actual;
		actual = this.os.createOrganization(organization);
		System.out.println(organization);
		System.out.println(actual);
	}
}
