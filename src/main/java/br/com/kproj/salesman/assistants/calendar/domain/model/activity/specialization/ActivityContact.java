package br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Contact;
import com.trex.shared.annotations.Model;

@Model
public class ActivityContact extends Activity {

    private Contact contact;

    public ActivityContact(){}

    public ActivityContact(Long id){
        this.setId(id);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
