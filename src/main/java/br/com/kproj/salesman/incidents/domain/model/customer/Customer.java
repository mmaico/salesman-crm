package br.com.kproj.salesman.incidents.domain.model.customer;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Customer extends ModelIdentifiable {

    private Long id;

    public Customer(Long id) {
        this.id = id;
    }

    public Customer() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
