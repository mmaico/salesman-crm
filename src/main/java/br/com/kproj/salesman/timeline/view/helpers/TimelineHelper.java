package br.com.kproj.salesman.timeline.view.helpers;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.PersonBuilder;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimelineHelper {


    @Autowired
    private TimelineApplication application;

    public Timeline findByProposal(BusinessProposalEntity proposal) {
        return application.register(proposal);
    }

    public Timeline findByContact(ContactEntity contact) {
        return application.register(contact);
    }

    public Timeline findByPerson(Person person) {
        return null; //application.register(PersonBuilder.createPerson(person.getId()).build());
    }
}
