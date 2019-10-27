package dev.rds.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.rds.entities.Event;

@Component
@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	

	//find Event by name
	Set<Event> findByName(String name);
	
	//find event by tag
	//Set<Event> findByTags(String tag);
	
	//by organization
	Set<Event> findByOrganization(String name);
}