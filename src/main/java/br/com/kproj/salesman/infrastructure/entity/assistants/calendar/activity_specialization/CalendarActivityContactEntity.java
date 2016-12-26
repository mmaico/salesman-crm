package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;

import javax.persistence.*;

@Entity
@Table(name="calendar_activity_contact")
public class CalendarActivityContactEntity extends Identifiable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private ContactEntity contact;

    @OneToOne
    @JoinColumn(name = "activity_id")
    private CalendarActivityEntity activity;


    public CalendarActivityContactEntity(){}

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalendarActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(CalendarActivityEntity activity) {
        this.activity = activity;
    }
}
