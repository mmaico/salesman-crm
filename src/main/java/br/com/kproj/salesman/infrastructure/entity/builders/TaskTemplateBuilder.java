package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;

public class TaskTemplateBuilder extends AbstractBuilder<TaskTemplateEntity>  {

	public TaskTemplateBuilder() {
		this.entity = new TaskTemplateEntity();
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
