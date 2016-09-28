package br.com.kproj.salesman.assistants.activities.domain.model.user;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace;
import br.com.kproj.salesman.assistants.activities.domain.services.*;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.assistants.activities.domain.model.user.OwnerBuilder.createOwner;

@Model
public class Owner extends ModelIdentifiable {

    private Long id;

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private ChecklistRepository checkListRepository;

    public Owner() {
        AutowireHelper.autowire(this);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChangeStatusActivity changeStatus(Activity activity) {
        return newStatus -> repository.changeStatus(activity, newStatus);
    }

    public SaveActivityInWorkspace save(Activity activity) {
        return (workspace) -> repository.save(activity);
    }

    public SaveSubActivityInWorkspace save(SubActivity subactivity) {
        return activityParent -> {
            subactivity.setParent(activityParent);
            subactivity.setOwner(createOwner(this.id).build());
            return repository.save(subactivity);
        };
    }

    public AddChecklistInActivity add(Checklist checklist) {
        return activity -> checkListRepository.newCheckList(checklist, activity);
    }

    public MasksAsCompletedActivity marks(Checklist checklist) {
        return () -> checklist.marksAsCompleted();
    }
}
