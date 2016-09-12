package br.com.kproj.salesman.delivery2.tasks.domain.model.checklist;


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
}
