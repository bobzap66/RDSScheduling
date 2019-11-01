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
		Account actual = ar.findByUsernameIgnoreCase(account.getUsername());
		if(actual == null) {
			return null;
		}
		if(account.getEmail() != null) {
			actual.setEmail(account.getEmail());
		}
		if(account.getPassword() != null) {
			actual.setPassword(account.getPassword());
		}
		if(account.getName() != null) {
			actual.setName(account.getName());
		}
		account = ar.save(actual);
		return account;
	}

	@Override
	public boolean deleteAccount(Account account) {
		Account actual = ar.findByUsernameIgnoreCase(account.getUsername());
		try {
			ar.delete(actual);
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
		Account actual = ar.findByUsernameIgnoreCase(username);
		if (actual == null ) {
			return null;
		} else if (actual.getPassword().equals(password) == false) {
			return null;
		} else {
		return actual;
		}
	}



	@Override
	public Account getAccountById(int id)
  {
		Account account = ar.findById(id);
		return account;
	}



	@Override
	public Account getAccountByUsername(String username) {
		Account account = ar.findByUsernameIgnoreCase(username);
		return account;
	}



	
	
}
