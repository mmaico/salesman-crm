package br.com.kproj.salesman.sales.domain.model.sales;

import br.com.kproj.salesman.infrastructure.model.CollectionBehavior;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;


public class Products extends CollectionBehavior<Saleable> {

    private final List<Saleable> saleables;

    public Products(List<Saleable> saleables) {
        this.saleables = saleables;
    }

    public Products() {
        this.saleables = Lists.newArrayList();
    }

    @Override
    public Collection<Saleable> getCollection() {
        return saleables;
    }
}
