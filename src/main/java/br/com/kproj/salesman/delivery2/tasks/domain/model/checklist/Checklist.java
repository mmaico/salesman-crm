package br.com.kproj.salesman.delivery2.tasks.domain.model.checklist;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Checklist extends ModelIdentifiable {

    private Long id;
    private String name;
    private Boolean done;

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
}
