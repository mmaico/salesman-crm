package br.com.kproj.salesman.infrastructure.entity.timeline;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.campaigns.Campaign;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="timelines")
public class Timeline extends Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="timeline_id")
    private List<TimelineActivity> activities;

    @OneToOne
    @JoinColumn(name="person_id")
    private Person person;

    @OneToOne
    @JoinColumn(name="business_proposal_id")
    private BusinessProposal proposal;

    @OneToOne
    @JoinColumn(name="contact_id")
    private ContactEntity contact;

    @OneToOne
    @JoinColumn(name="task_id")
    private Task task;

    @OneToOne
    @JoinColumn(name="personal_activity_id")
    private PersonalActivity personalActivity;

    @OneToOne
    @JoinColumn(name="campaign_id")
    private Campaign campaign;

    @OneToOne
    @JoinColumn(name="incident_id")
    private Incident incident;

    @OneToOne
    @JoinColumn(name="lead_id")
    private Incident lead;

    public  Timeline() {}

    public Timeline (Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TimelineActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<TimelineActivity> activities) {
        this.activities = activities;
    }

    public void addActivity(TimelineActivity activity) {
        if (this.activities == null) {
            this.activities = Lists.newArrayList();
        }

        this.activities.add(activity);
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

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public PersonalActivity getPersonalActivity() {
        return personalActivity;
    }

    public void setPersonalActivity(PersonalActivity personalActivity) {
        this.personalActivity = personalActivity;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public Incident getLead() {
        return lead;
    }

    public void setLead(Incident lead) {
        this.lead = lead;
    }
}
