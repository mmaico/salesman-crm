package br.com.kproj.salesman.timelines.activities.domain.model.timeline;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Timeline extends ModelIdentifiable {

    private Long id;


    public Timeline() {}
    public Timeline(Long id) {
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
