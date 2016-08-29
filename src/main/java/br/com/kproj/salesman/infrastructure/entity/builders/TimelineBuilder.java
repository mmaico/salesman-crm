package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;

public class TimelineBuilder extends AbstractBuilder<Timeline>  {

	public TimelineBuilder() {
		this.entity = new Timeline();
	}

	public TimelineBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public TimelineBuilder(Person person) {
		this();
		this.entity.setPerson(person);
	}
	
	public TimelineBuilder(ContactEntity contact) {
		this();
		this.entity.setContact(contact);
	}

	public TimelineBuilder(Task task) {
		this();
		this.entity.setTask(task);
	}
	
	public TimelineBuilder(BusinessProposalEntity businessProposalEntity) {
		this();
		this.entity.setProposal(businessProposalEntity);
	}

	public TimelineBuilder(PersonalActivity personal) {
		this();
		this.entity.setPersonalActivity(personal);
	}
	
	public TimelineBuilder withContact(ContactEntity contact) {
		this.entity.setContact(contact);
		return this;
	}

    public TimelineBuilder withPerson(Person person) {
        this.entity.setPerson(person);
        return this;
    }

    public TimelineBuilder withBusinessProposal(BusinessProposalEntity proposal) {
        this.entity.setProposal(proposal);
        return this;
    }

	public TimelineBuilder withPersonalActivity(PersonalActivity personalActivity) {
		this.entity.setPersonalActivity(personalActivity);
		return this;
	}

    public TimelineBuilder withItem(TimelineActivity item) {
        if (this.entity.getActivities() == null) {
            this.entity.setActivities(Lists.newArrayList());
        }

        this.entity.getActivities().add(item);
        return this;
    }

    public TimelineBuilder withItems(List<TimelineActivity> items) {
        if (this.entity.getActivities() == null) {
            this.entity.setActivities(Lists.newArrayList());
        }

        this.entity.getActivities().addAll(items);
        return this;
    }

	public static TimelineBuilder createTimeline(Long id) {
		return new TimelineBuilder(id);
	}

	public static TimelineBuilder createTimeline() {
		return new TimelineBuilder();
	}
	
	public static TimelineBuilder createTimeline(TimelinePresent timelinePresent) {

		if (timelinePresent instanceof Person) {
			return new TimelineBuilder((Person)timelinePresent);
		} else if (timelinePresent instanceof ContactEntity) {
			return new TimelineBuilder((ContactEntity) timelinePresent);
		} else if (timelinePresent instanceof BusinessProposalEntity) {
			return new TimelineBuilder((BusinessProposalEntity) timelinePresent);
		} else if (timelinePresent instanceof Task) {
			return new TimelineBuilder((Task) timelinePresent);
		} else if (timelinePresent instanceof PersonalActivity) {
			return new TimelineBuilder((PersonalActivity) timelinePresent);
		} else {
			throw new ValidationException(Sets.newHashSet("timeline.builder.invalid.type"));
		}

	}

}
