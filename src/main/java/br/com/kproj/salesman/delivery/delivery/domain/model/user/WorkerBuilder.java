package br.com.kproj.salesman.delivery.delivery.domain.model.user;


import br.com.kproj.salesman.delivery.delivery.domain.model.delivery.Delivery;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class WorkerBuilder extends AbstractBuilder<Worker>  {

	public WorkerBuilder() {
		this.entity = new Worker();
	}

	public WorkerBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public WorkerBuilder withUser(Long userId) {
		this.entity.setUser(new User(userId));
		return this;
	}

	public WorkerBuilder withDelivery(Long deliveryId) {
		this.entity.setDelivery(new Delivery(deliveryId));
		return this;
	}

	public static WorkerBuilder createWorker(Long id) {
		return new WorkerBuilder(id);
	}

	public static WorkerBuilder createWorker() {
		return new WorkerBuilder();
	}
}
