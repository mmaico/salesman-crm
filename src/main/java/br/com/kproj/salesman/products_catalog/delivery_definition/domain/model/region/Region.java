package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Region extends ModelIdentifiable {

    private Long id;

    public Region(Long id) {
        this.id = id;
    }

    public Region() {}

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
