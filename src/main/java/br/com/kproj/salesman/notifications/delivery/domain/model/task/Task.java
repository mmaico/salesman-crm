package br.com.kproj.salesman.notifications.delivery.domain.model.task;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Task extends ModelIdentifiable {

    private Long id;

    public Task(Long id) {
        this.id = id;
    }

    public Task() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
