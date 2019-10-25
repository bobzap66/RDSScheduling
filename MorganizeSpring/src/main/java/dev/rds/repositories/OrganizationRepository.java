package dev.rds.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

@Component
@Repository
public interface OrganizationRepository extends CrudRepository <Organization, Integer> {
	
	List<Organization> findOrganizationByName(String name);
	//List<Organization> findOrganizationByTag(Tag tag);

}
