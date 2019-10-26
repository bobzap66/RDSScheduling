package RepositoryTest;

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


	
	 @Test
	 @Rollback
	 void createAccount() 
	 {
		 Account account = new Account(); 
		 account.setName("test");
		 account.setUsername("AccountRepositoryTestUsernameRollback");
		 account.setPassword("testPassword"); 
		 account.setEmail("test@email.com");
		 
		 ar.save(account);
		 
		 Assert.assertNotNull(account);
	 }
	 
	
	@Test
	void findByUsername() 
	{
		try
		{
			Account account = ar.findByUsername("AccountRepositoryTestUsername");
			Assert.assertNotNull(account);
		}
		catch(NullPointerException e)
		{
			Assert.fail();
		}
	}
	
	@Test
	@Rollback
	void deleteAccount()
	{
		try
		{
			ar.delete(ar.findByUsername("AccountRepositoryTestUsername"));
			Assert.assertTrue(true);
		}
		catch(IllegalArgumentException e)
		{
			Assert.fail();
		}
		
	}

}






































