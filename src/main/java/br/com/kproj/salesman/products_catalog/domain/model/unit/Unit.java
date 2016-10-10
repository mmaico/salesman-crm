package br.com.kproj.salesman.products_catalog.domain.model.unit;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.shared.annotations.Model;


@Model
public class Unit extends ModelIdentifiable {

    private Long id;
    private String name;

    public Unit(Long id) {
        this.id = id;
    }
    public Unit() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
