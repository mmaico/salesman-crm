package br.com.kproj.salesman.assistants.activities.domain.model.user;


import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.workspace.Workspace;
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

    //DSL
    public Owner changeStatus(Activity activity) {
        this.context.add(Activity.class, activity);
        return this;
    }

    public void toNewStatus(Status newStatus) {
        Activity activity = (Activity) this.context.get(Activity.class);
        repository.changeStatus(activity, newStatus);

    }

    public Owner save(Activity activity) {
        this.context.add(Activity.class, activity);
        return this;
    }

    public Owner save(SubActivity activity) {
        this.context.add(SubActivity.class, activity);
        return this;
    }

    public Optional<Activity> ofYour(Workspace workspace) {
        Activity activity = (Activity) this.context.get(Activity.class);
        return repository.save(activity);
    }

    public Optional<SubActivity> childOf(Activity parent) {
        SubActivity subactivity = (SubActivity) this.context.get(SubActivity.class);
        subactivity.setParent(parent);
        subactivity.setOwner(createOwner(this.id).build());
        return repository.save(subactivity);
    }

    public Owner add(Checklist checklist) {
        this.context.add(Checklist.class, checklist);
        return this;
    }

    public Optional<Checklist> in(Activity activity) {
        Checklist checklist = (Checklist) this.context.get(Checklist.class);
        return checkListRepository.newCheckList(checklist, activity);
    }

    public Owner marks(Checklist checklist) {
        this.context.add(Checklist.class, checklist);
        return this;
    }

    public void asCompleted() {
        Checklist checklist = (Checklist) this.context.get(Checklist.class);
        checklist.marksAsCompleted();
    }
}
