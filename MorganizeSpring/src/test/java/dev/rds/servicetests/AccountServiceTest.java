package dev.rds.servicetests;

import static org.junit.jupiter.api.Assertions.*;

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
import dev.rds.services.AccountService;
import junit.framework.Assert;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class AccountServiceTest {

	@Autowired
	AccountService as;
	
	
	@Test
	@Rollback
	public void loginTest() {
		Account account = new Account();
		account.setUsername("DungeonMaster5");
		account.setPassword("1234");
		boolean result;
		Account actual = this.as.login(account);
		result = actual.getEmail().equals("Aquaman@gmail.com");
		assertEquals(true, result);
	}
	
	@Test
	@Rollback
	public void deleteAccountTest() {
		Account account = this.as.getAccountById(1008);
		boolean result = this.as.deleteAccount(account);
		assertEquals(true,result);
	}
	
	@Test
	@Rollback
	public void getAccountByUsernameTest() {
		Account account = this.as.getAccountByUsername("godFather");
		boolean result = "Godfather".equals(account.getUsername());
		assertEquals(true,result);
	}
	
	@Test
	@Rollback
	public void getUpdateAccountTest() {
		Account account = this.as.getAccountByUsername("godFather");
		account.setEmail("NoEmail");
		Account actual = this.as.updateAccount(account);
		boolean result = account.getEmail().equals(actual.getEmail());
		assertEquals(true,result);
	}
	
	
}
