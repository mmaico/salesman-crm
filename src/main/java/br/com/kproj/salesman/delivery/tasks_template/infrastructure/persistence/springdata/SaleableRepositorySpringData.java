package br.com.kproj.salesman.delivery.tasks_template.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("saleableTemplateRepository")
public interface SaleableRepositorySpringData extends BaseRepositoryLegacy<SaleableUnitEntity, Long> {


}
