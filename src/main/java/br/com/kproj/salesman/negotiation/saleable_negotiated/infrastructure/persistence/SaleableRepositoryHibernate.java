package br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.saleable.SalePackageEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.Saleable;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackageBuilder;
import br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleableRepository;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.PackageRepositorySpringData;
import br.com.kproj.salesman.negotiation.saleable_negotiated.infrastructure.persistence.springdata.SaleableUnitRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static br.com.kproj.salesman.negotiation.saleable_negotiated.domain.model.saleable.SaleablePackageBuilder.createPackage;
import static java.util.Optional.ofNullable;

@Repository
public class SaleableRepositoryHibernate extends BaseRespositoryImpl<Saleable, SaleableUnitEntity> implements SaleableRepository {

    @Autowired
    private SaleableUnitRepositorySpringData repository;

    @Autowired
    private PackageRepositorySpringData packageRepository;


    @Override
    public BaseRepositoryLegacy<SaleableUnitEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SaleableUnitEntity, Saleable> getConverter() {
        return (saleableEntity, args) -> {
            Optional<SalePackageEntity> packageEntity = ofNullable(packageRepository.findOne(saleableEntity.getId()));

            if (packageEntity.isPresent()) {
                SaleablePackageBuilder saleablePackage = createPackage(packageEntity.get().getId());
                packageEntity.get()
                        .getRelations().forEach(rEntity -> saleablePackage.addSaleable(rEntity.getSaleable().getId()));
                return saleablePackage.build();
            } else {
                return SaleableBuilder.createSaleable(saleableEntity.getId()).build();
            }
        };
    }
}
