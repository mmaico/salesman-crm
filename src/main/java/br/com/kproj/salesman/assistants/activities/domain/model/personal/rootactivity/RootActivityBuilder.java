package br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;


public class RootActivityBuilder extends AbstractBuilder<RootActivity>  {

	public RootActivityBuilder() {
		this.entity = new RootActivity();
	}

	public RootActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}


	public static RootActivityBuilder createRootTask(Long id) {
		return new RootActivityBuilder(id);
	}

	public static RootActivityBuilder createRootTask() {
		return new RootActivityBuilder();
	}
}
