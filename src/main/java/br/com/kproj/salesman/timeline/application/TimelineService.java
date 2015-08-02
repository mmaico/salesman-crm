package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface TimelineService extends ModelService<Timeline> {

    Timeline register(Timeline timeline, Person person);

    Timeline register(Timeline timeline, BusinessProposal proposal);

    Timeline register(Timeline timeline, Contact contact);

}
