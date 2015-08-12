package br.com.kproj.salesman.infrastructure.entity.timeline;


import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="timelines")
public class Timeline extends Identifiable {


    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="department_id")
    private List<TimelineItem> items;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposal proposal;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    public  Timeline() {}

    public Timeline (Long id) {
        this.id = id;
    }

    public List<TimelineItem> getItems() {
        return items;
    }

    public void setItems(List<TimelineItem> items) {
        this.items = items;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BusinessProposal getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposal proposal) {
        this.proposal = proposal;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
