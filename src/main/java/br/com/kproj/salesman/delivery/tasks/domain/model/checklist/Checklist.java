package br.com.kproj.salesman.delivery.tasks.domain.model.checklist;

import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.google.common.collect.Sets;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;

@Model
public class Checklist extends ModelIdentifiable {

    private Long id;
    private String name;
    private Boolean done;
    private Task task;

    @Autowired
    private ChecklistRepository repository;

    public Checklist() {
        AutowireHelper.autowire(this);
    }

    public Checklist(Long id) {
        this();
        this.id = id;
    }

    public void marksAsCompleted() {
        Optional<Checklist> checkList = repository.findOne(this.id);

        if (!checkList.isPresent()) {
            hasErrors(Sets.newHashSet("invalid.checklist.to.change.status"))
                    .throwing(ValidationException.class);
        }

        repository.complete(checkList.get());
    }




    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDone() {
        return done;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
