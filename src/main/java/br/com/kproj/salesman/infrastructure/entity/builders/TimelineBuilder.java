package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import com.google.common.collect.Lists;

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
	
	public TimelineBuilder(Contact contact) {
		this();
		this.entity.setContact(contact);
	}

	public TimelineBuilder(Task task) {
		this();
		this.entity.setTask(task);
	}
	
	public TimelineBuilder(BusinessProposal businessProposal) {
		this();
		this.entity.setProposal(businessProposal);
	}
	
	public TimelineBuilder withContact(Contact contact) {
		this.entity.setContact(contact);
		return this;
	}

    public TimelineBuilder withPerson(Person person) {
        this.entity.setPerson(person);
        return this;
    }

    public TimelineBuilder withBusinessProposal(BusinessProposal proposal) {
        this.entity.setProposal(proposal);
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
	
	public static TimelineBuilder createTimeline(Person person) {
		return new TimelineBuilder(person);
	}
	
	public static TimelineBuilder createTimeline(Contact contact) {
		return new TimelineBuilder(contact);
	}
	
	public static TimelineBuilder createTimeline(BusinessProposal proposal) {
		return new TimelineBuilder(proposal);
	}

	public static TimelineBuilder createTimeline(Task task) {
		return new TimelineBuilder(task);
	}
}
