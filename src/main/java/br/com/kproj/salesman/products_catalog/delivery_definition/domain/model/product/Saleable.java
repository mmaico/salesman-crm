package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Saleable extends ModelIdentifiable {

    private Long id;


    public Saleable(Long id) {
        this();
        this.id = id;
    }

    public Saleable() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
