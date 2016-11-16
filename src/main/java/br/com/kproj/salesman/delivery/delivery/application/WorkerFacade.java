package br.com.kproj.salesman.delivery.delivery.application;




import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.Worker;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerIn;
import br.com.kproj.salesman.delivery.delivery.domain.model.user.WorkerOut;
import br.com.kproj.salesman.infrastructure.service.ModelFacade;

import java.util.Collection;

public interface WorkerFacade extends ModelFacade<Worker> {

    Worker register(WorkerIn workerIn);
    void delete(WorkerOut workerOut);

    Collection<Worker> findAll(Delivery delivery);

}
