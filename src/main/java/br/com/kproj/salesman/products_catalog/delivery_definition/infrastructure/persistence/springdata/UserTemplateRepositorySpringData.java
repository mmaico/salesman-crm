package br.com.kproj.salesman.products_catalog.delivery_definition.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("userTemplateRepository")
public interface UserTemplateRepositorySpringData extends BaseRepositoryLegacy<UserEntity, Long> {



}
