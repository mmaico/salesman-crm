package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;


import br.com.kproj.salesman.delivery.tasks.domain.model.delivery.Delivery;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

import java.util.Date;


public class TaskBuilder extends AbstractBuilder<Task>  {

	public TaskBuilder() {
		this.entity = new Task();
	}

	public TaskBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public TaskBuilder withTitle(String title) {
		this.entity.setTitle(title);
		return this;
	}

	public TaskBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	public TaskBuilder withDeadline(Date date) {
		this.entity.setDeadline(date);
		return this;
	}

	public TaskBuilder withDelivery(Long deliveryId) {
		this.entity.setDelivery(new Delivery(deliveryId));
		return this;
	}

	public TaskBuilder withStatus(TaskStatus status) {
		this.entity.setStatus(status);
		return this;
	}


	public static TaskBuilder createTask(Long id) {
		return new TaskBuilder(id);
	}

	public static TaskBuilder createTask() {
		return new TaskBuilder();
	}
}
