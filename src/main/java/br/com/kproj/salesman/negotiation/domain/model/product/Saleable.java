package br.com.kproj.salesman.negotiation.domain.model.product;

import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;

import java.math.BigDecimal;


public class Saleable extends ModelIdentifiable {

    private Long id;

    private BigDecimal price;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
