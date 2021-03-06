package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.util.Arrays.asList;

@Transactional
@Service
public abstract class BaseModelServiceLegacyImpl<T extends Identifiable> implements ModelLegacyService<T> {

    @Override
    public T save(T entity, DomainBusinessRulesLegacy... checkrules) {

        if (entity == null) {
            return entity;
        }

        if (entity.isNew()) {
            asList(checkrules).stream()
                    .forEach(check -> check.checkBusinessRulesFor(entity));
            return getRepository().save(entity);
        } else {
            T entityLoaded = getRepository().findOne(entity.getId());

            if (entityLoaded == null) {
                throw new IllegalArgumentException("entity.to.update.not.exist");
            }

            BeanUtils.create().copyProperties(entityLoaded, entity);

            asList(checkrules).stream()
                            .forEach(check -> check.checkBusinessRulesFor(entityLoaded));

            return getRepository().save(entityLoaded);
        }
    }

    public Iterable<T> findAll(Pageable pager) {
        return getRepository().findAll(pager);
    }
    
    public Optional<T> getOne(Long id) {
    	
    	if (id == null) {
    		return Optional.empty();
    	}
    	
    	T entity = getRepository().findOne(id);
    	
    	return Optional.ofNullable(entity);
    }

    public abstract BaseRepositoryLegacy<T, Long> getRepository();
}
