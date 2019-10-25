package RepositoryTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.rds.entities.Account;
import dev.rds.repositories.AccountRepository;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = dev.rds.app.MorganizeSpringApplication.class)
class AccountRepositoryTest 
{
	@Autowired
	AccountRepository ar;

	private Account createTestAccount()
	{
		Account testAccount = new Account();
		testAccount.setUsername("testUsername");
		testAccount.setName("test");
		testAccount.setPassword("password1");
		testAccount.setEmail("test@email.com");
		testAccount.setMemberships(null);
		
		return testAccount;
	}
	
	@Test
	@Order(1)
	void createAccount()
	{
		Account account = this.createTestAccount();
		account = ar.save(account);
		assertNotNull(account);
	
	}
	
	
	@Test
	@Order(2)
	void findByUsername() 
	{
		Account account = ar.findByUsernameIgnoreCase("test");
		Account comp = this.createTestAccount();
		comp.setId(account.getId());
		assertEquals(account, comp);
		
	}
	
	@Test
	@Order(3)
	void deleteAccount()
	{
		ar.delete(ar.findByUsernameIgnoreCase("test"));
		Account account = ar.findByUsernameIgnoreCase("test");
		assertEquals(null, account);
	}

}






































