package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class RootTaskBuilder extends AbstractBuilder<RootTask>  {

	public RootTaskBuilder() {
		this.entity = new RootTask();
	}

	public RootTaskBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public static RootTaskBuilder createRootTask(Long id) {
		return new RootTaskBuilder(id);
	}

	public static RootTaskBuilder createRootTask() {
		return new RootTaskBuilder();
	}
}
