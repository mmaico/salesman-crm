package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;


import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.subscribe.ChangeStatus;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends BaseRepository<Task, Long> {


    Iterable<Task> findAll(Delivery delivery, Pageable pageable);

    void generateTaskFor(SalesOrder salesOrder);

    void changeStatus(ChangeStatus changeStatus);
}
