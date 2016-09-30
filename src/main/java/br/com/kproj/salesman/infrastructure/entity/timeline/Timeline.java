package br.com.kproj.salesman.infrastructure.entity.timeline;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.IncidentEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.campaigns.CampaignEntity;
import br.com.kproj.salesman.infrastructure.entity.leads.LeadEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
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
    private BusinessProposalEntity proposal;

    @OneToOne
    @JoinColumn(name="contact_id")
    private ContactEntity contact;

    @OneToOne
    @JoinColumn(name="task_id")
    private TaskEntity task;

    @OneToOne
    @JoinColumn(name="personal_activity_id")
    private PersonalActivityEntity personalActivity;

    @OneToOne
    @JoinColumn(name="campaign_id")
    private CampaignEntity campaign;

    @OneToOne
    @JoinColumn(name="incident_id")
    private IncidentEntity incident;

    @OneToOne
    @JoinColumn(name="lead_id")
    private LeadEntity lead;


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

    public BusinessProposalEntity getProposal() {
        return proposal;
    }

    public void setProposal(BusinessProposalEntity proposal) {
        this.proposal = proposal;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public PersonalActivityEntity getPersonalActivity() {
        return personalActivity;
    }

    public void setPersonalActivity(PersonalActivityEntity personalActivity) {
        this.personalActivity = personalActivity;
    }

    public CampaignEntity getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public IncidentEntity getIncident() {
        return incident;
    }

    public void setIncident(IncidentEntity incident) {
        this.incident = incident;
    }

    public LeadEntity getLead() {
        return lead;
    }

    public void setLead(LeadEntity lead) {
        this.lead = lead;
    }
}
