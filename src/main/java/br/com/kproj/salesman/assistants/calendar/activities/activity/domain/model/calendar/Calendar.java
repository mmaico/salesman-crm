package br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.calendar;


import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.calendar.activities.activity.domain.model.activity.NewActivity.newActivity;
import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Calendar extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ActivityRepository repository;

    public Calendar() {
        autowire(this);
    }

    public Calendar(Long id) {
        autowire(this);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Activity> addNew(Activity activity) {
        return repository.register(newActivity(this, activity));
    }
}
