package dev.rds.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

@Component
@Repository
public interface OrganizationRepository extends CrudRepository <Organization, Integer> {
	
	Set<Organization> findOrganizationByName(String name);
	Set<Organization> findOrganizationByTags(Tag tag);

}
