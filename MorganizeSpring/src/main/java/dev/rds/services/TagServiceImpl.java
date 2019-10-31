package dev.rds.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Event;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.repositories.TagRepository;

@Component
@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagRepository tr;

	@Override
	public Tag createTag(Tag tag) {
		tag = tr.save(tag);
		return tag;
	}

	@Override
	public Tag getTagById(int id) {
		Tag tag = tr.findById(id).orElse(null);
		return tag;
	}

	@Override
	public Tag getTagByTag(String tag) {
		Tag t = new Tag();
		t = tr.findByTagIgnoreCase(tag);
		return t;
	}

	@Override
	public Set<Tag> getTagsByEvent(Event event) {
		Set<Tag> tags = tr.findByEvents(event);
		return tags;
	}

	@Override
	public Set<Tag> getTagsByOrganization(Organization organization) {
		Set<Tag> tags = tr.findByOrganizations(organization);
		return tags;
	}

	@Override
	public Set<Tag> getTagsByTag(String tag) {
		Set<Tag> tags = tr.findByTagContainingIgnoreCase(tag);
		return tags;
	}

}
