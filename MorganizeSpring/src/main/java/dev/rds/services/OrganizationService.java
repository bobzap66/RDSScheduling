package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Account;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

public interface OrganizationService {
	
	//Create
	Organization createOrganization(Organization organization, Account account);
	
	//Read
	Organization getOrganizationById(int id);
	Set<Organization> searchOrganizationsByTag(String tag);
	Set<Organization> getAllOrganizations();
	
	//Update
	Organization updateOrganization(Organization organization);
	
	//Delete
	boolean deleteOrganization(Organization organization);

	
}
