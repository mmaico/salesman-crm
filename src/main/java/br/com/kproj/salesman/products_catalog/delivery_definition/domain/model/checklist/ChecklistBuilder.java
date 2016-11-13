package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;

public class ChecklistBuilder extends AbstractBuilder<Checklist>  {

	public ChecklistBuilder() {
		this.entity = new Checklist();
	}

	public ChecklistBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public ChecklistBuilder withName(String name) {
		this.entity.setName(name);
		return this;
	}

	public ChecklistBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}

	public ChecklistBuilder withTask(Long taskId) {
		this.entity.setTask(new Task(taskId));
		return this;
	}


	public static ChecklistBuilder createChecklist(Long id) {
		return new ChecklistBuilder(id);
	}

	public static ChecklistBuilder createChecklist() {
		return new ChecklistBuilder();
	}
}
