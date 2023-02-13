package br.com.kproj.salesman.assistants.activities.domain.model.user;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.services.AddChecklistInActivity;
import br.com.kproj.salesman.assistants.activities.domain.services.SaveSubActivityInWorkspace;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Model
public class Owner extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private SubActivityRepository subActivityRepository;

    @Autowired
    private ChecklistRepository checkListRepository;

    public Owner() {
        AutowireHelper.autowire(this);
    }

    public Owner(Long id) {
        this();
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Optional<Activity> save(Activity activity) {
        return repository.save(activity);
    }

    public SaveSubActivityInWorkspace save(SubActivity subActivity) {
        return (rootActivity -> {
           subActivity.setParent(rootActivity);
           return subActivityRepository.register(subActivity);
        });
    }

    public Activity update(Activity activity) {
        return repository.update(activity);
    }


    public AddChecklistInActivity add(Checklist checklist) {
        return activity -> checkListRepository.newCheckList(checklist, activity);
    }

    public Checklist update(Checklist checklist) {
        return checkListRepository.update(checklist);
    }

    public static Owner owner() {
        return new Owner();
    }
}
