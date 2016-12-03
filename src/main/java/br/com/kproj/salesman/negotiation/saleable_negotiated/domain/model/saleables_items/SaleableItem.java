package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.negotiated.Negotiated;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackage;
import com.trex.shared.annotations.Model;

@Model
public class SaleableItem extends ModelIdentifiable {

    private Long id;

    private Saleable saleable;

    private SaleablePackage usedPackage;

    private Negotiated negotiated;

    public SaleableItem() {}
    public SaleableItem(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Saleable getSaleable() {
        return saleable;
    }

    public void setSaleable(Saleable saleable) {
        this.saleable = saleable;
    }

    public SaleablePackage getUsedPackage() {
        return usedPackage;
    }

    public void setUsedPackage(SaleablePackage usedPackage) {
        this.usedPackage = usedPackage;
    }

    public Negotiated getNegotiated() {
        return negotiated;
    }

    public void setNegotiated(Negotiated negotiated) {
        this.negotiated = negotiated;
    }
}
