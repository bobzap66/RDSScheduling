package dev.rds.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.rds.entities.Account;
import dev.rds.entities.Organization;
import dev.rds.entities.Tag;
import dev.rds.entities.Type;
import dev.rds.repositories.OrganizationRepository;

@Component
@Service
public class OrganizationServiceImpl implements OrganizationService{
	
	@Autowired
	OrganizationRepository or;
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	TagService ts;

	@Override
	public Organization createOrganization(Organization organization, Account account) {
		Set<Tag> tags = organization.getTags();
		Set<Tag> newTags = new HashSet<Tag>();
		if(tags != null) {
			for(Tag tag : tags) {
				//If the tag doesn't doesn't exist in the database, 
				//remove the tag from the set of tags attached to the event
				// and add the tag returned by the database
				if(ts.getTagByTag(tag.getTag()) == null)
				{
					tag = ts.createTag(tag);
					newTags.add(tag);
					tag.getOrganizations().add(organization);
				} else {
					tag = ts.getTagByTag(tag.getTag());
					newTags.add(tag);
					tag.getOrganizations().add(organization);
				}
			}
			organization.setTags(newTags);
		}
		organization = or.save(organization);
		ms.createMembership(account, organization, Type.ADMIN);
		return organization;
	}

	@Override
	public Organization getOrganizationById(int id) {
		Organization organization = or.findById(id).orElse(null);
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		Set<Tag> tags = organization.getTags();
		Set<Tag> newTags = new HashSet<Tag>();
		if(tags != null) {
			for(Tag tag : tags) {
				//If the tag doesn't doesn't exist in the database, 
				//remove the tag from the set of tags attached to the event
				// and add the tag returned by the database
				if(ts.getTagByTag(tag.getTag()) == null) {
					tag = ts.createTag(tag);
					newTags.add(tag);
					tag.getOrganizations().add(organization);
				} else {
					tag = ts.getTagByTag(tag.getTag());
					newTags.add(tag);
					if(!tag.getOrganizations().contains(organization)) {
						tag.getOrganizations().add(organization);
					}
				}
			}
			organization.setTags(newTags);
		}
		organization = or.save(organization);
		return organization;
	}

	@Override
	public boolean deleteOrganization(Organization organization) {
		try {
			or.delete(organization);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Set<Organization> searchOrganizationsByTag(String tag) {
		Tag actual = ts.getTagByTag(tag);
		if(actual == null) {
			return null;
		}
		Set<Organization> organizations = or.findOrganizationByTags(actual);
		return organizations;
	}

	@Override
	public Set<Organization> getAllOrganizations()
	{
		Set<Organization> organizations = or.findAll();
		return organizations;
	}

}
