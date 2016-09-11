package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

public class ChecklistBuilder extends AbstractBuilder<ChecklistEntity>  {

	public ChecklistBuilder() {
		this.entity = new ChecklistEntity();
	}

	public ChecklistBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ChecklistBuilder withName(String name) {
        this.entity.setName(name);
        return this;
    }

    public ChecklistBuilder withDone(Boolean isDone) {
        this.entity.setIsDone(isDone);
        return this;
    }

    public ChecklistBuilder withTask(TaskEntity taskEntity) {
        this.entity.setTask(taskEntity);
        return this;
    }

	
	public static ChecklistBuilder createChecklist(Long id) {
		return new ChecklistBuilder(id);
	}

	public static ChecklistBuilder createChecklist() {
		return new ChecklistBuilder();
	}
}
