package br.com.kproj.salesman.assistants.calendar.domain.model.user;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.service.ActivityInCalendarService;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    public User(Long id) {
        this.id = id;
    }

    public User() {}

    public ActivityInCalendarService addAn(Activity activity) {
        return calendar -> calendar.addNew(activity);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static User user() {
        return new User();
    }
}
