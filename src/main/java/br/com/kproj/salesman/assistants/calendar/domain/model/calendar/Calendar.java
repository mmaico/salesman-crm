package br.com.kproj.salesman.assistants.calendar.domain.model.calendar;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.ActivityRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.assistants.calendar.domain.model.activity.NewActivity.newActivity;
import static br.com.kproj.salesman.infrastructure.helpers.AutowireHelper.autowire;

@Model
public class Calendar extends ModelIdentifiable {

    private Long id;

    private List<Activity> activities;

    private User owner;

    @Autowired
    private ActivityRepository repository;

    public Calendar(){
        autowire(this);
    }
    public Calendar(Long id){
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Optional<Activity> addNew(Activity activity) {
        return repository.register(newActivity(this, activity));
    }
}
