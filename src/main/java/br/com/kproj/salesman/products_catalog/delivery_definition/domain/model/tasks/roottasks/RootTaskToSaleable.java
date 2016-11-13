package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.roottasks;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;


public class RootTaskToSaleable implements ValueObject {

    private final Long saleableId;
    private final RootTask task;

    public RootTaskToSaleable(Long saleableId, RootTask task) {
        this.saleableId = saleableId;
        this.task = task;
    }

    public Long getSaleableId() {
        return saleableId;
    }

    public RootTask getTask() {
        return task;
    }

    public Saleable getAsASaleable() {
        return new Saleable(saleableId);
    }
}
