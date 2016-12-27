package br.com.kproj.salesman.assistants.activities.domain.model.user;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Assigner extends ModelIdentifiable {



    private Long id;

    public Assigner() {}
    public Assigner(Long id) {
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
