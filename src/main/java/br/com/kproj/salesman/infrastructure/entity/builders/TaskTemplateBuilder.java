package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;

public class TaskTemplateBuilder extends AbstractBuilder<TaskTemplate>  {

	public TaskTemplateBuilder() {
		this.entity = new TaskTemplate();
	}

	public TaskTemplateBuilder(Long id) {
		this();
		this.entity.setId(id);
	}



	public static TaskTemplateBuilder createTaskTemplateBuilder(Long id) {
		return new TaskTemplateBuilder(id);
	}

	public static TaskTemplateBuilder createTaskTemplateBuilder() {
		return new TaskTemplateBuilder();
	}
}
