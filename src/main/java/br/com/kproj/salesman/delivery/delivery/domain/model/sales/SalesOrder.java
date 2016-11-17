package br.com.kproj.salesman.delivery.delivery.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;

@Model
public class SalesOrder extends ModelIdentifiable {

    private Long id;

    public SalesOrder(Long id) {
        this.id = id;
    }
    public SalesOrder() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
