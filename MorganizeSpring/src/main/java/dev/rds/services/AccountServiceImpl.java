package dev.rds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.repositories.AccountRepository;

@Component
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository ar;
	
	@Override
	public Account createAccount(Account account) {
		account = ar.save(account);
		return account;
	}

	

	@Override
	public Account updateAccount(Account account) {
		account = ar.save(account);
		return account;
	}

	@Override
	public boolean deleteAccount(Account account) {
		try {
			ar.delete(account);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}



	@Override
	public Account login(Account account) {
		String username;
		String password;
		username = account.getUsername();
		password = account.getPassword();
		Account actual = ar.findByUsername(username);
		if (actual == null ) {
			return null;
		} else if (actual.getPassword().equals(password) == false) {
			return null;
		} else {
		return actual;
		}
	}



	
	
}
