package dev.rds.services;

import dev.rds.entities.Organization;

public interface OrganizationService {
	
	//Create
	Organization createOrganization(Organization organization);
	
	//Read
	Organization getOrganizationById(int id);
	
	//Update
	Organization updateOrganization(Organization organization);
	
	//Delete
	boolean deleteOrganization(Organization organization);

}
