package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.subtask;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask.RootTask;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class SubtaskBuilder extends AbstractBuilder<Subtask>  {

	public SubtaskBuilder() {
		this.entity = new Subtask();
	}

	public SubtaskBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SubtaskBuilder withParent(RootTask parent) {
		this.entity.setParent(parent);
		return this;
	}

	public static SubtaskBuilder createSubtask(Long id) {
		return new SubtaskBuilder(id);
	}

	public static SubtaskBuilder createSubtask() {
		return new SubtaskBuilder();
	}
}
