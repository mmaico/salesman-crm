package br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity;


import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.Contact;
import com.trex.shared.annotations.Model;

@Model
public class ActivityContact extends Activity {

    private Contact contact;

    public ActivityContact(){
    }

    public ActivityContact(Long id) {
        super.setId(id);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
