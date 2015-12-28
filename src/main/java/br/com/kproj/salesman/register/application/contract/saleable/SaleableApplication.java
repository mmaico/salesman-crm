package br.com.kproj.salesman.register.application.contract.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableType;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnit;
import br.com.kproj.salesman.infrastructure.service.ModelService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleableApplication extends ModelService<SaleableUnit> {


    List<SaleableUnit> getByType(SaleableType saleableType);
}
