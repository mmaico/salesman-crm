package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineItem;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface TimelineActivitiesService extends ModelService<Timeline> {

    Timeline register(Person person, TimelineItem item);

    Timeline register(BusinessProposal proposal, TimelineItem item);

    Timeline register(Contact contact, TimelineItem item);

}
