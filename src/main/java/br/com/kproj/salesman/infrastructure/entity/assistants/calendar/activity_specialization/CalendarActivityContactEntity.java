package br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="calendar_activity_contact")
public class CalendarActivityContactEntity extends CalendarActivityEntity {

    @ManyToOne
    @JoinColumn(name="contact_id")
    private ContactEntity contact;


    public CalendarActivityContactEntity(){}

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }
}
