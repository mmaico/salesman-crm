package br.com.kproj.salesman.products_catalog.view.support.operations;

import br.com.kproj.salesman.infrastructure.http.response.handler.resources.Operation;
import br.com.kproj.salesman.products_catalog.domain.model.salepackage.SalePackage;

@FunctionalInterface
public interface PackageOperation {

    void execute(SalePackage salePackage, Operation operation);
}
