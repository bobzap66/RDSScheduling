package dev.rds.repositories;

import org.springframework.data.repository.CrudRepository;

import dev.rds.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	Account findByUsername(String username);
	
}
