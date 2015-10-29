package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleableServiceImpl extends BaseModelServiceImpl<SaleableUnit> implements SaleableService {



    private SaleableUnitRepository saleableRepository;

    @Autowired
    public SaleableServiceImpl(SaleableUnitRepository saleableRepository) {
        this.saleableRepository = saleableRepository;
    }


    @Override
    public BaseRepository<SaleableUnit, Long> getRepository() {
        return saleableRepository;
    }

}
