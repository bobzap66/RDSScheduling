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
	
	@Autowired
	AccountService as;
	
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
		for(Membership membership : memberships) {
			accounts.add(membership.getAccount());
		}
		return accounts;
	}

	@Override
	public Set<Membership> getMembershipsByAccount(Account account) {
		Set<Membership> memberships = mr.findAllByAccount(account);
		return memberships;
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

	@Override
	public Membership getMembershipByOrganizationAndAccount(Organization organization, Account account) {
		if(account == null) {
			return null;
		}
		if(organization == null) {
			return null;
		}
		Membership membership = mr.findByOrganizationAndAccount(organization, account);
		return membership;
	}

}
