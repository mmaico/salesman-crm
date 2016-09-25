package br.com.kproj.salesman.infrastructure.entity.timeline;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.IncidentEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.campaigns.CampaignEntity;
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
    private TaskEntity taskEntity;

    @OneToOne
    @JoinColumn(name="personal_activity_id")
    private PersonalActivityEntity personalActivityEntity;

    @OneToOne
    @JoinColumn(name="campaign_id")
    private CampaignEntity campaignEntity;

    @OneToOne
    @JoinColumn(name="incident_id")
    private IncidentEntity incidentEntity;

    @OneToOne
    @JoinColumn(name="lead_id")
    private IncidentEntity lead;

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

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public PersonalActivityEntity getPersonalActivityEntity() {
        return personalActivityEntity;
    }

    public void setPersonalActivityEntity(PersonalActivityEntity personalActivityEntity) {
        this.personalActivityEntity = personalActivityEntity;
    }

    public CampaignEntity getCampaignEntity() {
        return campaignEntity;
    }

    public void setCampaignEntity(CampaignEntity campaignEntity) {
        this.campaignEntity = campaignEntity;
    }

    public IncidentEntity getIncidentEntity() {
        return incidentEntity;
    }

    public void setIncidentEntity(IncidentEntity incidentEntity) {
        this.incidentEntity = incidentEntity;
    }

    public IncidentEntity getLead() {
        return lead;
    }

    public void setLead(IncidentEntity lead) {
        this.lead = lead;
    }
}
