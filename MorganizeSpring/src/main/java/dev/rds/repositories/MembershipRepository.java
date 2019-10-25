package dev.rds.repositories;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Account;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Type;

@Component
@Repository
public interface MembershipRepository {
	
	Set<Membership> findAllByAccount(Account account);
	Set<Membership> findAllByOrganization(Organization organizaion);
	
	Set<Membership> findAllByAccountAndType(Account account, Type type);
	Set<Membership> findAllByOrganizationAndType(Account account, Type type);

}
