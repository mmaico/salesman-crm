package br.com.kproj.salesman.products_catalog.domain.model.saleables.salepackage;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import com.trex.shared.annotations.EntityReference;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Model
public class SalePackage extends SaleableUnit {

    @EntityReference(SaleableUnitEntity.class)
    private List<SaleableUnit> saleables = new ArrayList<>();

    @Autowired
    private SalePackageRepository repository;

    public SalePackage(Long id) {
        super(id);
    }

    public SalePackage() {
        super();
    }

    public List<SaleableUnit> getSaleables() {
        return saleables;
    }

    public void setSaleables(List<SaleableUnit> saleables) {
        this.saleables = saleables;
    }

    public void addSaleable(SaleableUnit saleableUnit) {
        this.saleables.add(saleableUnit);
        repository.addSaleable(this, saleableUnit);
    }

    public void removeSaleable(SaleableUnit saleableUnit) {
        this.saleables.remove(saleableUnit);
        repository.removeSaleable(this, saleableUnit);
    }
}
