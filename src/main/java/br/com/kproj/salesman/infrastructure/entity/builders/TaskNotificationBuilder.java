package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.task.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

import java.util.Date;

public class TaskNotificationBuilder extends AbstractBuilder<ScheduleTriggerNotification>  {

	public TaskNotificationBuilder() {
		this.entity = new ScheduleTriggerNotification();
	}

	public TaskNotificationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public TaskNotificationBuilder withDate(Date date) {
		this.entity.setTriggerDate(date);
		return this;
	}

	public TaskNotificationBuilder withTask(TaskEntity taskEntity) {
		this.entity.setTaskEntity(taskEntity);
		return this;
	}





	public static TaskNotificationBuilder createNotification(Long id) {
		return new TaskNotificationBuilder(id);
	}

	public static TaskNotificationBuilder createNotification() {
		return new TaskNotificationBuilder();
	}
	


}
