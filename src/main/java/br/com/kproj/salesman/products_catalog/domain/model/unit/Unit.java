package br.com.kproj.salesman.products_catalog.domain.model.unit;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import lombok.Data;

@Data
public class Unit extends Identifiable {

    private Long id;
    private String name;

    public Long getId() {
        return this.id;
    }


}
