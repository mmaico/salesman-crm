package br.com.kproj.salesman.notifications.delivery.domain.model.notification;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.notifications.delivery.domain.model.task.Task;
import br.com.kproj.salesman.notifications.delivery.domain.model.user.Receiver;

import java.util.Date;

public class NotificationBuilder extends AbstractBuilder<Notification>  {

	public NotificationBuilder() {
		this.entity = new Notification();
	}

	public NotificationBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public NotificationBuilder withReceiver(Receiver receiver) {
		this.entity.setReceiver(receiver);
		return this;
	}

	public NotificationBuilder createNow() {
		this.entity.setCreateDate(new Date());
		return this;
	}

	public NotificationBuilder withCreation(Date date) {
		this.entity.setCreateDate(date);
		return this;
	}

	public NotificationBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}

	public static NotificationBuilder createView(Long id) {
		return new NotificationBuilder(id);
	}

	public static NotificationBuilder createView() {
		return new NotificationBuilder();
	}

}
