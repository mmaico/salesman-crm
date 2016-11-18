package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence;

import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.DeliveryRepository;
import br.com.kproj.salesman.delivery.delivery.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.DeliveryRepositorySpringdata;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.springdata.predicates.DeliveryFiltersDefinition;
import br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.translate.WorkerEntityToWorkerConverter;
import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.entity.delivery.QDeliveryEntity;
import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository("deliveryRepositoryHibernateDeliveryModule")
public class DeliveryRepositoryHibernate extends BaseRespositoryImpl<Delivery, DeliveryEntity> implements DeliveryRepository {


    private DeliveryRepositorySpringdata repository;
    private WorkerEntityToWorkerConverter converter;
    private DeliveryFiltersDefinition filtersDefinition;

    @PersistenceContext
    private EntityManager em;


    @Autowired
    public DeliveryRepositoryHibernate(DeliveryRepositorySpringdata repository, WorkerEntityToWorkerConverter converter,
                                       DeliveryFiltersDefinition filtersDefinition) {
        this.repository = repository;
        this.converter = converter;
        this.filtersDefinition = filtersDefinition;
    }


    @Override
    public Iterable<Delivery> findAll(FilterAggregator filters, Pageable pager) {
        final QDeliveryEntity deliveryEntity = QDeliveryEntity.deliveryEntity;

        JPQLQuery<DeliveryEntity> queryBase = new JPAQuery<DeliveryEntity>(em).from(deliveryEntity);
        BooleanBuilder booleanBuilder = filtersDefinition.buildCriteria(filters, deliveryEntity);
        JPQLQuery<DeliveryEntity> query = queryBase.where(booleanBuilder);
        long totalItems = query.fetchCount();

        List<DeliveryEntity> result = query.limit(pager.getPageSize())
                .offset(pager.getOffset())
                .fetch();

        List<Delivery> resulModel = result.stream().map(item -> getConverter().convert(item))
                .collect(Collectors.toList());

        return new PageImpl<>(resulModel, pager, totalItems);
    }


    @Override
    public BaseRepositoryLegacy<DeliveryEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<DeliveryEntity, Delivery> getConverter() {
        return (deliveryEntity, args) -> {
            Delivery delivery = new Delivery();
            delivery.setId(deliveryEntity.getId());
            delivery.setDeliveryForecast(deliveryEntity.getDeliveryForecast());
            delivery.setSalesOrder(new SalesOrder(deliveryEntity.getSalesOrder().getId()));

            deliveryEntity.getWorkers().stream()
                    .forEach(workerEntity -> delivery.addWorker(converter.convert(workerEntity)));

            return delivery;
        };
    }


}
