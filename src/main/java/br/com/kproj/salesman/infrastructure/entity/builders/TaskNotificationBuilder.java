package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.notification.ScheduleTriggerNotification;
import br.com.kproj.salesman.infrastructure.entity.task.Task;

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

	public TaskNotificationBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}





	public static TaskNotificationBuilder createNotification(Long id) {
		return new TaskNotificationBuilder(id);
	}

	public static TaskNotificationBuilder createNotification() {
		return new TaskNotificationBuilder();
	}
	


}
