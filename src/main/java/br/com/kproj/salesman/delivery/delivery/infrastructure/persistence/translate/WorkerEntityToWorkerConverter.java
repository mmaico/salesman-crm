package br.com.kproj.salesman.delivery.delivery.infrastructure.persistence.translate;

import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.User;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.infrastructure.entity.delivery.WorkerEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;


@Component
public class WorkerEntityToWorkerConverter implements Converter<WorkerEntity, Worker> {



    @Override
    public Worker convert(WorkerEntity workerEntity, Object... args) {

        Worker worker = new Worker();
        worker.setId(workerEntity.getId());
        worker.setDelivery(new Delivery(workerEntity.getDelivery().getId()));
        worker.setUser(new User(workerEntity.getUser().getId()));

        return worker;
    }
}
