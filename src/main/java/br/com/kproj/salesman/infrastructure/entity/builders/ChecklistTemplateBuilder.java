package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;

public class ChecklistTemplateBuilder extends AbstractBuilder<ChecklistTemplateEntity>  {

	public ChecklistTemplateBuilder() {
		this.entity = new ChecklistTemplateEntity();
	}

	public ChecklistTemplateBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ChecklistTemplateBuilder withName(String name) {
        this.entity.setName(name);
        return this;
    }



    public ChecklistTemplateBuilder withTaskTemplate(TaskTemplateEntity tasktemplate) {
        this.entity.setTaskTemplate(tasktemplate);
        return this;
    }

	public static ChecklistTemplateBuilder createChecklistTemplateBuilder(Long id) {
		return new ChecklistTemplateBuilder(id);
	}

	public static ChecklistTemplateBuilder createChecklistTemplateBuilder() {
		return new ChecklistTemplateBuilder();
	}
}
