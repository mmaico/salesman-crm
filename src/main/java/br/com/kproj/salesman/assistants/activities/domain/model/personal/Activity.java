package br.com.kproj.salesman.assistants.activities.domain.model.personal;

import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Assigner;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Model
public class Activity extends ModelIdentifiable {

    private Long id;

    private String title;
    private String description;
    private Date deadline;
    private Status status;

    private List<SubActivity> activities;

    private Owner owner;
    private Assigner assigner;

    @Autowired
    private ChecklistRepository repository;

    public Activity() {
        AutowireHelper.autowire(this);
    }


    public Optional<Checklist> addChecklist(Checklist checklist) {
        return repository.newCheckList(checklist, this);
    }







    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<SubActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<SubActivity> activities) {
        this.activities = activities;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Assigner getAssigner() {
        return assigner;
    }

    public void setAssigner(Assigner assigner) {
        this.assigner = assigner;
    }


}
