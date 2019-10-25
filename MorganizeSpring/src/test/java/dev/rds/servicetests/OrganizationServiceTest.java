package dev.rds.servicetests;


import org.junit.Assert;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
	@Rollback
	public void createOrganizationTest() {
		Organization organization = new Organization();
		organization.setName("Pirate Cosplay");
		organization.setDescription("We like to dress up like pirates and scare people on the street.");
		Organization actual;
		actual = this.os.createOrganization(organization);
		boolean result = organization.getName().equals(actual.getName());
		Assert.assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void getOrganizationByIdTest() {
		Organization organization = this.os.getOrganizationById(3000);
		String name = "D&D Winners";
		boolean result = name.equals(organization.getName());
		Assert.assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void updateOrganizationTest() {
		Organization organization = this.os.getOrganizationById(3000);
		String name = "TestName";
		organization.setName(name);
		organization = this.os.updateOrganization(organization);
		organization = this.os.getOrganizationById(3000);
		boolean result = name.equals(organization.getName());
		Assert.assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void deleteOrganization() {
		Organization organization = this.os.getOrganizationById(3000);
		boolean result = this.os.deleteOrganization(organization);
		Assert.assertEquals(true, result);
	}
}
