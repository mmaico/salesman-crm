package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.Package;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.PackageRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.PackageService;
import br.com.kproj.salesman.register.domain.contract.SaleableUnitDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PackageServiceImpl extends BaseModelServiceImpl<Package> implements PackageService {

    private SaleableUnitDomainService domainService;

    private PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, SaleableUnitDomainService domainService) {
        this.packageRepository = packageRepository;
        this.domainService = domainService;
    }

    @Override
    public Package register(Package packageItem) {
        domainService.verifyPreconditionToSave(packageItem);
        return super.save(packageItem);
    }

    public Optional<Package> getOne(Long id) {
        return packageRepository.getOne(id);
    }

    @Override
    public BaseRepository<Package, Long> getRepository() {
        return packageRepository;
    }

}
