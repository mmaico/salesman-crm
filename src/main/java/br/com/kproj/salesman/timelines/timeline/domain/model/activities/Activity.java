package br.com.kproj.salesman.timelines.timeline.domain.model.activities;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Activity extends ModelIdentifiable {


    private Long id;

    public Activity() {}
    public Activity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
