package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleableApplicationImpl extends BaseModelServiceImpl<SaleableUnit> implements SaleableApplication {



    private SaleableUnitRepository saleableRepository;

    @Autowired
    public SaleableApplicationImpl(SaleableUnitRepository saleableRepository) {
        this.saleableRepository = saleableRepository;
    }


    @Override
    public BaseRepository<SaleableUnit, Long> getRepository() {
        return saleableRepository;
    }

}
