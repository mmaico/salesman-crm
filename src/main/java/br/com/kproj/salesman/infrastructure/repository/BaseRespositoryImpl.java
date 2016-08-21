package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.model.ModelIdentifiable;
import com.trex.clone.BusinessModelClone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseRespositoryImpl<T extends ModelIdentifiable, ENTITY extends Identifiable> implements BaseRepository<T, Long> {

    @Override
    public Page<T> findAll(Pageable page) {
        Page<ENTITY> productsEntities = getRepository().findAll(page);

        List<T> converteds = productsEntities.getContent().stream()
                .map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return new PageImpl<>(converteds, page, productsEntities.getTotalElements());
    }

    @Override
    public Optional<T> findOne(Long id) {
        Optional<ENTITY> result = Optional.ofNullable(getRepository().findOne(id));

        if (!result.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(getConverter().convert(result.get()));
        }

    }

    @Override
    public Optional<T> save(T entity) {


        if (entity.isNew()) {
            ENTITY resultEntity = BusinessModelClone.from(entity).convertTo(getEntityClass());
            return Optional.ofNullable(getConverter().convert(getRepository().save(resultEntity)));
        } else {
            Optional<ENTITY> productEntity = Optional.ofNullable(getRepository().findOne(entity.getId()));
            BusinessModelClone.from(entity).merge(productEntity.get());
            return Optional.ofNullable(getConverter().convert(productEntity.get()));
        }
    }

    public abstract BaseRepositoryLegacy<ENTITY, Long> getRepository();

    public abstract Converter<ENTITY, T> getConverter();

    public Class<ENTITY> getEntityClass() {
        return (Class<ENTITY>) ((ParameterizedType)
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
