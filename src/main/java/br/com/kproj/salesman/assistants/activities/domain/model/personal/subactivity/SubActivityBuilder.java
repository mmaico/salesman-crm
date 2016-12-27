package br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask.Subtask;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class SubActivityBuilder extends AbstractBuilder<Subtask>  {

	public SubActivityBuilder() {
		this.entity = new Subtask();
	}

	public SubActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SubActivityBuilder withParent(RootTask parent) {
		this.entity.setParent(parent);
		return this;
	}

	public static SubActivityBuilder createSubtask(Long id) {
		return new SubActivityBuilder(id);
	}

	public static SubActivityBuilder createSubtask() {
		return new SubActivityBuilder();
	}
}
