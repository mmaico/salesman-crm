package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="incidents")
public class Incident extends Identifiable implements TimelinePresent {

	private static final long serialVersionUID = -7486201820229036695L;

    public enum IncidentStatus {
        OPEN, STATED, RESOLVED
    }

    public enum IncidentPriority {
        LOW, MEDIUM, HIGH
    }

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person client;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
    @NotNull
	private IncidentStatus status;

    @OneToOne(mappedBy = "incident")
    private Timeline timeline;

    //Colocar canal de origem (telefone, email, web)

    @ManyToOne
    @JoinColumn(name="responsible_id")
    @NotNull
    private UserEntity responsible;

    @ManyToOne
    @JoinColumn(name="created_id")
    @NotNull
    private UserEntity createdBy;

    @Column(name="priority")
    @Enumerated(EnumType.STRING)
    @NotNull
    private IncidentPriority priority;

    public Incident() {}

    public Incident(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }

    public UserEntity getResponsible() {
        return responsible;
    }

    public void setResponsible(UserEntity responsible) {
        this.responsible = responsible;
    }

    public IncidentPriority getPriority() {
        return priority;
    }

    public void setPriority(IncidentPriority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
