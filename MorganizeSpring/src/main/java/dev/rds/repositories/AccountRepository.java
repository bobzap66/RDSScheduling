package dev.rds.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import dev.rds.entities.Account;

@Component
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	
	Account findByUsernameIgnoreCase(String username);
	Account findById(int id);
}
