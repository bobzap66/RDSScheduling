package dev.rds.services;

import java.util.Set;

import dev.rds.entities.Event;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;

public interface TagService {

	// Create
	Tag createTag(Tag tag);
	
	// Read
	Tag getTagById(int id);
	Tag getTagByTag(String tag);
	Set<Tag> getTagsByTag(String tag);
	
	Set<Tag> getTagsByEvent(Event event);
	Set<Tag> getTagsByOrganization(Organization organization);
	
	// Update
	
	// Delete
	
}
