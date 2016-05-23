package br.com.kproj.salesman.infrastructure.entity;

import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="incidents")
public class Incident extends Identifiable implements TimelinePresent {

	private static final long serialVersionUID = -7486201820229036695L;

    public enum IncidentStatus {
        OPEN, STATED, FIXED
    }

    public enum IncidentPriority {
        LOW, MEDIUM, HIGH
    }

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
	private IncidentStatus status;

    @OneToOne(mappedBy = "incident")
    private Timeline timeline;

    //Colocar canal de origem (telefone, email, web)

    @ManyToOne
    @JoinColumn(name="responsible_id")
    private User responsible;

    @Column(name="priority")
    @Enumerated(EnumType.STRING)
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public IncidentPriority getPriority() {
        return priority;
    }

    public void setPriority(IncidentPriority priority) {
        this.priority = priority;
    }
}
