package br.com.kproj.salesman.products_catalog.catalog.domain.model.salepackage;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnit;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Model
public class SalePackage extends SaleableUnit {


    private List<SaleableRelation> relations = new ArrayList<>();

    @Autowired
    private SalePackageRepository repository;

    public SalePackage(Long id) {
        super(id);
        AutowireHelper.autowire(this);
    }

    public SalePackage() {
        super();
        AutowireHelper.autowire(this);
    }

    public void addSaleable(SaleableUnit saleableUnit) {
        repository.addSaleable(this, saleableUnit);
    }

    public void removeSaleable(SaleableRelation relation) {
        repository.removeRelation(relation);
    }

    public List<SaleableRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<SaleableRelation> relations) {
        this.relations = relations;
    }
}
