package br.com.kproj.salesman.delivery.tasks.domain.model.checklist;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;

public class ChecklistForTask {

    private final Checklist checklist;
    private final Long taskId;


    public ChecklistForTask(Checklist checklist, Long taskId) {
        this.checklist = checklist;
        this.taskId = taskId;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Task getTask() {
        return new Task(this.taskId);
    }

    public static ChecklistForTask createChecklistToTask(Long taskId, Checklist checklist) {
        return new ChecklistForTask(checklist, taskId);
    }
}
