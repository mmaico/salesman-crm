package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("saleableRepositoryCalendarModule")
public interface SaleableEntityRepositorySpringData extends BaseRepositoryLegacy<SaleableUnitEntity, Long> {



}
