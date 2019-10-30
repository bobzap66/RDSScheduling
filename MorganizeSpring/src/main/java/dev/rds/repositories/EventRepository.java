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
public interface EventRepository extends CrudRepository<Event, Integer>{
	

	//find Event by name
	Set<Event> findByName(String name);
	
	//return all events
	Set<Event> findAll();
	
	//find event by tag
	Set<Event> findByTags(Tag tag);
	
	//by organization
	Set<Event> findByOrganization(Organization organization);
}
