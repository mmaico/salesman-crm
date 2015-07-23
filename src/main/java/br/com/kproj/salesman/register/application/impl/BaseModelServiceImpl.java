package br.com.kproj.salesman.register.application.impl;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.register.application.ModelService;

@Transactional
@Service
public abstract class BaseModelServiceImpl<T extends Identifiable> implements ModelService<T> {

    @Override
    public T save(T entity) {

        if (entity == null) {
            return entity;
        }

        if (entity.isNew()) {
            return getRepository().save(entity);
        } else {
            T entityLoaded = getRepository().findOne(entity.getId());

            if (entityLoaded == null) {
                throw new IllegalArgumentException("entity.to.update.not.exist");
            }

            BeanUtils.create().copyProperties(entityLoaded, entity);

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

    public abstract BaseRepository<T, Long> getRepository();
}
