package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionHelper {

    @Autowired
    private RegionRepository repository;

    public Iterable<OperationRegionEntity> getRegions() {
        return  repository.findAll();
    }
}
