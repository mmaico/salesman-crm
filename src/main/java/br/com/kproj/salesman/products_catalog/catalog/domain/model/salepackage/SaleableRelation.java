package br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import com.github.mmaico.shared.annotations.Model;


@Model
public class SaleableRelation extends ModelIdentifiable {

    private Long id;

    private SaleableUnit saleable;

    public SaleableRelation() {}
    public SaleableRelation(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SaleableUnit getSaleable() {
        return saleable;
    }

    public void setSaleable(SaleableUnit saleable) {
        this.saleable = saleable;
    }
}
