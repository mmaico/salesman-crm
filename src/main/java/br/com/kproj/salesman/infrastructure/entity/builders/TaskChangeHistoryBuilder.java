package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskChangeHistory;

import java.util.Date;

public class TaskChangeHistoryBuilder extends AbstractBuilder<TaskChangeHistory>  {

	public TaskChangeHistoryBuilder() {
		this.entity = new TaskChangeHistory();
	}

	public TaskChangeHistoryBuilder(TaskEntity taskEntity, TaskStatusEntity status) {
		this();
		this.entity.setStatusChanged(status);
        this.entity.setTaskEntityChanged(taskEntity);
        this.entity.setDateOfChange(new Date());
	}

	public static TaskChangeHistoryBuilder createTaskChangeHistory(TaskEntity taskEntity, TaskStatusEntity status) {
		return new TaskChangeHistoryBuilder(taskEntity, status);
	}

}
