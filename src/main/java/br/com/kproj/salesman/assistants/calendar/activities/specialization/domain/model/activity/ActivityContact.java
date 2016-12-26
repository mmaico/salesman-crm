package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.Contact;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class ActivityContact extends ModelIdentifiable {

    private Long id;

    private Contact contact;

    public ActivityContact(Long id){
        this.id = id;
    }

    public ActivityContact(){
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
