package br.com.kproj.salesman.products_catalog.catalog.infrastructure.persistence.springdata.impl;


import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.Represent;

public interface SaleableUnitCustomRepository {

    void represent(Long id, Represent represent);
}
