package br.com.kproj.salesman.delivery2.tasks.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Region extends ModelIdentifiable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
