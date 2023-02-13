package br.com.kproj.salesman.timelines.activities.domain.model.user;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityRepository;
import br.com.kproj.salesman.timelines.activities.domain.services.ActivityInTimelineService;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Model
public class User extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ActivityRepository repository;


    public User(Long id) {
        this();
        this.id = id;
    }

    public User() {
        AutowireHelper.autowire(this);
    }

    public ActivityInTimelineService createNew(Activity activity) {
        return timeline -> {
            activity.setTimeline(timeline);
            return repository.register(activity);
        };
    }

    public Activity update(Activity activity) {
        return repository.update(activity);
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
