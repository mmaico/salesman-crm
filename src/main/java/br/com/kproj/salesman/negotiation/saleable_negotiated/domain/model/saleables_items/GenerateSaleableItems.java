package br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleables_items;


import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;

public class GenerateSaleableItems implements ValueObject{

    private final Saleable saleable;

    public GenerateSaleableItems(Saleable saleable) {
        this.saleable = saleable;
    }

    public Saleable getSaleable() {
        return saleable;
    }

    public static GenerateSaleableItems generateSaleableItems(Saleable saleable) {
        return new GenerateSaleableItems(saleable);
    }
}
