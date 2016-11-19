package br.com.kproj.salesman.delivery.tasks.domain.model.delivery;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class Delivery extends ModelIdentifiable {

    private Long id;


    public Delivery() {}
    public Delivery(Long id) {
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
