package dev.rds.repositories;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Type;

public interface MembershipRepository {
	
	Set<Membership> findAllByAccount(Account account);
	Set<Membership> findAllByOrganization(Organization organizaion);
	
	Set<Membership> findAllByAccountAndType(Account account, Type type);
	Set<Membership> findAllByOrganizationAndType(Account account, Type type);

}
