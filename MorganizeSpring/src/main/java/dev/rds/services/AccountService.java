package dev.rds.services;

import dev.rds.entities.Account;

public interface AccountService {
	

	Account createAccount(Account account);
	
	Account login(Account account);
	
	Account updateAccount(Account account);
	
	boolean deleteAccount(Account account);

}
