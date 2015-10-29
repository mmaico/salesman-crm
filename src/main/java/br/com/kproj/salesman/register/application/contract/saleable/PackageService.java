package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.service.ModelService;
import br.com.kproj.salesman.infrastructure.entity.saleable.Package;

public interface PackageService extends ModelService<Package> {

    Package register(Package packageItem);

}
