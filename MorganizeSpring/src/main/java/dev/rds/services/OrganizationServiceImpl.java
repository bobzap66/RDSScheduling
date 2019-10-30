package dev.rds.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.entities.Type;
import dev.rds.repositories.OrganizationRepository;

@Component
@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository or;
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	TagService ts;

	@Override
	public Organization createOrganization(Organization organization, Account account) {
		organization = or.save(organization);
		ms.createMembership(account, organization, Type.ADMIN);
		return organization;
	}

	@Override
	public Organization getOrganizationById(int id) {
		Organization organization = or.findById(id).get();
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		organization = or.save(organization);
		return organization;
	}

	@Override
	public boolean deleteOrganization(Organization organization) {
		try {
			or.delete(organization);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Set<Organization> searchOrganizationsByTag(String tag) {
		Tag actual = ts.getTagByTag(tag);
		if(actual == null) {
			return null;
		}
		Set<Organization> organizations = or.findOrganizationByTags(actual);
		return organizations;
	}

	@Override
	public Set<Organization> getAllOrganizations()
	{
		Set<Organization> organizations = or.findAll();
		return organizations;
	}

}
