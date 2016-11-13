package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.checklist;

import br.com.kproj.salesman.infrastructure.model.ValueObject;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;


public class ChecklistToTask implements ValueObject {

    private final Long taskId;
    private final Checklist checklist;

    public ChecklistToTask(Long taskId, Checklist checklist) {
        this.taskId = taskId;
        this.checklist = checklist;
    }

    public ChecklistToTask(Checklist checklist) {
        this(null, checklist);
    }

    public Long getTaskId() {
        return taskId;
    }

    public Task getTask() {
        return new Task(this.taskId);
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public static ChecklistToTask createChecklistToTask(Long taskId, Checklist checklist) {
        return new ChecklistToTask(taskId, checklist);
    }

    public static ChecklistToTask createChecklistToTask(Checklist checklist) {
        return new ChecklistToTask(checklist);
    }
}
