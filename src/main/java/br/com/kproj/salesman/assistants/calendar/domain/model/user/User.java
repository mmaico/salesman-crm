package br.com.kproj.salesman.assistants.calendar.domain.model.user;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.calendar.Calendar;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Optional;

@Model
public class User extends ModelIdentifiable {

    private Long id;


    public User addAn(Activity activity) {
        this.context.add(Activity.class, activity);
        return this;
    }

    public Optional<Activity> in(Calendar calendar) {
        Activity activity = (Activity) this.context.get(Activity.class);
        return calendar.addNew(activity);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
