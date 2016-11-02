package br.com.kproj.salesman.products_catalog.domain.model.salepackage;

import br.com.kproj.salesman.infrastructure.helpers.AutowireHelper;
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnit;
import com.trex.shared.annotations.Attribute;
import com.trex.shared.annotations.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Model
public class SalePackage extends SaleableUnit {

    @Attribute(destinationName = "saleableUnits")
    private List<SaleableUnit> saleables = new ArrayList<>();

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
