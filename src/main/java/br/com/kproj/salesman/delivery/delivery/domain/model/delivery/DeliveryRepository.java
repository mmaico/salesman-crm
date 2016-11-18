package br.com.kproj.salesman.delivery.delivery.domain.model.delivery;


import br.com.kproj.salesman.infrastructure.helpers.FilterAggregator;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

public interface DeliveryRepository extends BaseRepository<Delivery, Long> {



    Iterable<Delivery> findAll(FilterAggregator filters, Pageable pager);
}
