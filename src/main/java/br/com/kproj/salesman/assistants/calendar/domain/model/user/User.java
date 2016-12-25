package br.com.kproj.salesman.assistants.calendar.domain.model.user;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.service.ActivityInCalendarService;
import br.com.kproj.salesman.assistants.calendar.domain.service.ActivityOfCalendarService;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    public User(Long id) {
        this();
        this.id = id;
    }

    public User() {
        AutowireHelper.autowire(this);
    }

    @Autowired
    private ActivityRepository repository;

    public ActivityInCalendarService addAn(Activity activity) {
        return calendar -> calendar.addNew(activity);
    }

    public Activity update(Activity activity) {
        return repository.update(activity);
    }

    public ActivityOfCalendarService getActivities() {
        return (calendar -> (filters, page) -> repository.findAll(calendar, filters, page));
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
