package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.region.Region;

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

	public TaskBuilder withQuantity(Integer quantity) {
		this.entity.setQuantityDaysToFinish(quantity);
		return this;
	}

	public TaskBuilder withSaleable(Saleable saleable) {
		this.entity.setSaleable(saleable);
		return this;
	}

	public TaskBuilder withRegion(Region region) {
		this.entity.setRegion(region);
		return this;
	}


	public static TaskBuilder createTask(Long id) {
		return new TaskBuilder(id);
	}

	public static TaskBuilder createTask() {
		return new TaskBuilder();
	}
}
