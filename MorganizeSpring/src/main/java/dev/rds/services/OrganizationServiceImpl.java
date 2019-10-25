package dev.rds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Organization;
import dev.rds.repositories.OrganizationRepository;

@Component
@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository or;

	@Override
	public Organization createOrganization(Organization organization) {
		organization = or.save(organization);
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

}