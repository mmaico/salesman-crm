package br.com.kproj.salesman.infrastructure.service;


import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public abstract class BaseModelServiceImpl<T extends ModelIdentifiable> implements ModelFacade<T> {

    @Override
    public Optional<T> register(T entity) {

        if (entity == null) {
            return Optional.empty();
        }

        return getRepository().save(entity);
    }

    public Iterable<T> findAll(Pageable pager) {
        return getRepository().findAll(pager);
    }
    
    public Optional<T> getOne(Long id) {
    	
    	if (id == null) {
    		return Optional.empty();
    	}
    	
    	return getRepository().findOne(id);
    }

    public abstract BaseRepository<T, Long> getRepository();
}
