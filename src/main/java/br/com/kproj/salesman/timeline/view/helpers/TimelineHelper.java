package br.com.kproj.salesman.timeline.view.helpers;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.builders.PersonBuilder;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimelineHelper {


    @Autowired
    private TimelineApplication application;

    public Timeline findByProposal(BusinessProposal proposal) {
        return application.register(proposal);
    }

    public Timeline findByContact(Contact contact) {
        return application.register(contact);
    }

    public Timeline findByPerson(Person person) {
        return application.register(PersonBuilder.createPerson(person.getId()).build());
    }
}
