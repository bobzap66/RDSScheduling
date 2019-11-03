package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

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
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Type;
import dev.rds.services.AccountService;
import dev.rds.services.MembershipService;
import dev.rds.services.OrganizationService;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class MembershipServiceTest {
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	AccountService as;
	
	@Autowired
	OrganizationService os;
	
	
	@Test
	@Rollback
	void createMembershipTest() {
		Account account = as.getAccountById(1012);
		Organization organization = os.getOrganizationById(3052);
		Membership membership = ms.createMembership(account, organization, Type.MEMBER);
		boolean result = false;
		if(("Robert Deniro".equals(membership.getAccount().getName()))&&(membership.getType() == Type.MEMBER) && ("Try hard org".equals(membership.getOrganization().getName()))) {
			result = true;
		}
		assertEquals(true, result);
	}

	@Test
	@Rollback
	void getOrganizationsByAccountTest() {
		Account account = this.as.getAccountById(1010);
		
		Set<Membership> memberships = ms.getMembershipsByAccount(account);
		Iterator<Membership> itr = memberships.iterator(); 
		boolean result = false;
		while(itr.hasNext()) { 
			if("D&D Winners".equals(itr.next().getOrganization().getName())) {
				result = true;
			};
		}
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	void getAccountsByOrganizationAndMemberTest() {
		Organization organization = this.os.getOrganizationById(3003);
		Set<Account> accounts = this.ms.getMembershipsByOrganizationAndType(organization, Type.MEMBER);
		Iterator<Account> itr = accounts.iterator(); 
		boolean result = false;
		while(itr.hasNext()) { 
			if("Joe Manganiello".equals(itr.next().getName())) {
				result = true;
			}
		}
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	void getAccountsByOrganizationAndAdminTest() {
		Organization organization = this.os.getOrganizationById(3005);
		Set<Account> accounts = this.ms.getMembershipsByOrganizationAndType(organization, Type.ADMIN);
		Iterator<Account> itr = accounts.iterator(); 
		boolean result = false;
		while(itr.hasNext()) { 
			if("Robert Deniro".equals(itr.next().getName())) {
				result = true;
			}
		}
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	void updateMembershipTest() {
		Membership membership = this.ms.getMembershipById(3);
		membership.setType(Type.ADMIN);
		membership = this.ms.updateMembership(membership);
		boolean result = membership.getType() == Type.ADMIN;
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	void testGetMembershipByOrganizationAndAccount() {
		Organization organization = os.getOrganizationById(3005);
		Account account = as.getAccountById(1012);
		Membership actual = this.ms.getMembershipById(4);
		Membership membership = this.ms.getMembershipByOrganizationAndAccount(organization, account);
		assertEquals(actual, membership);
	}
}
