package br.com.kproj.salesman.assistants.calendar.calendar.domain.model.calendar;


import br.com.kproj.salesman.assistants.calendar.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Calendar extends ModelIdentifiable {

    private Long id;

    private User owner;


    public Calendar() {
    }

    public Calendar(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
