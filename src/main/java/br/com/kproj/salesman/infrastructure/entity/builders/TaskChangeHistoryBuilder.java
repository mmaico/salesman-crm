package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskChangeHistory;

import java.util.Date;

public class TaskChangeHistoryBuilder extends AbstractBuilder<TaskChangeHistory>  {

	public TaskChangeHistoryBuilder() {
		this.entity = new TaskChangeHistory();
	}

	public TaskChangeHistoryBuilder(Task task, TaskStatus status) {
		this();
		this.entity.setStatusChanged(status);
        this.entity.setTaskChanged(task);
        this.entity.setDateOfChange(new Date());
	}

	public static TaskChangeHistoryBuilder createTaskChangeHistory(Task task, TaskStatus status) {
		return new TaskChangeHistoryBuilder(task, status);
	}

}
