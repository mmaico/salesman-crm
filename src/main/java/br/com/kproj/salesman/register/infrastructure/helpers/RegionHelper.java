package br.com.kproj.salesman.register.infrastructure.helpers;

import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionHelper {

    @Autowired
    private RegionRepository repository;

    public Iterable<OperationRegion> getRegions() {
        return  repository.findAll();
    }
}
