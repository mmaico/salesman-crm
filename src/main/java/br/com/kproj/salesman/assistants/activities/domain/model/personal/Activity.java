package br.com.kproj.salesman.assistants.activities.domain.model.personal;

import br.com.kproj.salesman.assistants.activities.domain.model.user.Assigner;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

import java.util.Date;

@Model
public class Activity extends ModelIdentifiable {

    private Long id;

    private String title;
    private String description;
    private Date deadline;
    private Status status;
    private Owner owner;
    private Assigner assigner;
    private Represent represent;

    public Activity(){}
    public Activity(Long id){
        this.id = id;
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

    public Represent getRepresent() {
        return represent;
    }

    public void setRepresent(Represent represent) {
        this.represent = represent;
    }
}
