package br.com.kproj.salesman.assistants.activities.domain.model.checklist;

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

    private Boolean isDone;

    @Autowired
    private ChecklistRepository repository;

    public Checklist() {
        AutowireHelper.autowire(this);
    }

    public void marksAsCompleted() {
        Optional<Checklist> checkList = repository.findOne(this.id);

        if (!checkList.isPresent()) {
            hasErrors(Sets.newHashSet("invalid.checklist.to.change.status")).throwing(ValidationException.class);
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

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
