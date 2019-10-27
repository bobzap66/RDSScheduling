package dev.rds.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.entities.Membership;
import dev.rds.entities.Organization;
import dev.rds.entities.Type;
import dev.rds.repositories.MembershipRepository;

@Component
@Service
public class MembershipServiceImpl implements MembershipService {

	
	@Autowired
	MembershipRepository mr;
	
	@Override
	public Membership getMembershipById(int id) {
		Membership membership = mr.findById(id).orElse(null);
		return membership;
	}

	@Override
	public Membership createMembership(Account account, Organization organization, Type type) {
		Membership membership = new Membership();
		membership.setAccount(account);
		membership.setOrganization(organization);
		membership.setType(type);
		membership = mr.save(membership);
		
		return membership;
	}

	@Override
	public Set<Account> getMembershipsByOrganizationAndType(Organization organization, Type type) {
		Set<Membership> memberships = mr.findAllByOrganizationAndType(organization, type);
		Set<Account> accounts = new HashSet<Account>();
		Iterator<Membership> itr = memberships.iterator(); 
		while(itr.hasNext()) { 
			accounts.add(itr.next().getAccount());
		}

		
		return accounts;
	}

	@Override
	public Set<Organization> getMembershipsByAccount(Account account) {
		Set<Membership> memberships = mr.findAllByAccount(account);
		Set<Organization> organizations = new HashSet<Organization>();
		Iterator<Membership> itr = memberships.iterator(); 
		while(itr.hasNext()) { 
			organizations.add(itr.next().getOrganization());
		}

		return organizations;
	}

	@Override
	public Membership updateMembership(Membership membership) {
		membership = mr.save(membership);
		return membership;
	}

	@Override
	public boolean deleteMembership(Membership membership) {
		try {
			mr.delete(membership);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

}
