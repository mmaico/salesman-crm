package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("salesOrderRepositorySpringdataTaskModule")
public interface SalesOrderRepositorySpringdata extends BaseRepositoryLegacy<SalesOrderEntity, Long> {


}
