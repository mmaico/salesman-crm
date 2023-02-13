package br.com.kproj.salesman.products_catalog.catalog.domain.model.unit;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.github.mmaico.shared.annotations.Model;


@Model
public class Unit extends ModelIdentifiable {

    private Long id;
    private String name;

    public Unit(Long id) {
        this.id = id;
    }
    public Unit(Long id, String name) {
        this.id = id;
        this.name = name;
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
