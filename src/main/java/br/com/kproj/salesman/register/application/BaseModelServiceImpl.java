package br.com.kproj.salesman.register.application;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.helpers.BeanUtils;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public abstract BaseRepository<T, Long> getRepository();
}
