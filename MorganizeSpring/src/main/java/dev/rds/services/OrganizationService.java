package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

public interface OrganizationService {
	
	//Create
	Organization createOrganization(Organization organization);
	
	//Read
	Organization getOrganizationById(int id);
	Set<Organization> searchOrganizationsByTag(Tag tag);
	
	//Update
	Organization updateOrganization(Organization organization);
	
	//Delete
	boolean deleteOrganization(Organization organization);

}
