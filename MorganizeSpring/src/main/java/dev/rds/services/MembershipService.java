package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Type;

public interface MembershipService {

	Membership getMembershipById(int id);
	Membership createMembership(Account account, Organization organization, Type type);
	
	Set<Account> getMembershipsByOrganizationAndType(Organization organization, Type type);
	Set<Organization> getMembershipsByAccount(Account account);
	
	Membership updateMembership(Membership membership);
	
	boolean deleteMembership(Membership membership);
}
