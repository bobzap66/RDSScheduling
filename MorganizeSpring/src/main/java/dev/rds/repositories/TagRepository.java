package dev.rds.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Event;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

@Component
@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
	
	Tag findByTag(String tag);
	
	Set<Tag> findByEvents(Event event);
	Set<Tag> findByOrganizations(Organization organization);

}
