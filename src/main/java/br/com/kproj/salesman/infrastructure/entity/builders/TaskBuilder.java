package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskCost;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;

import java.util.Date;
import java.util.List;

public class TaskBuilder extends AbstractBuilder<Task>  {

	public TaskBuilder() {
		this.entity = new Task();
	}

	public TaskBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public TaskBuilder withTitle(String title) {
        this.entity.setTitle(title);
        return this;
    }

    public TaskBuilder withDescription(String description) {
        this.entity.setDescription(description);
        return this;
    }

    public TaskBuilder withDeadline(Date deadline) {
        this.entity.setDeadline(deadline);
        return this;
    }

    public TaskBuilder withStatus(TaskStatus status) {
        this.entity.setStatus(status);
        return this;
    }

    public TaskBuilder withSalesOrder(SalesOrder order) {
        this.entity.setSalesOrder(order);
        return this;
    }

    public TaskBuilder withSignedBy(List<User> users) {
        this.entity.setSignedBy(users); ;
        return this;
    }

    public TaskBuilder withNotifications(List<Notification> notifications) {
        this.entity.setNotifications(notifications);
        return this;
    }

    public TaskBuilder withTimeline() {
        Timeline timeline = new Timeline();

        this.entity.setTimeline(timeline);
        return this;
    }

    public TaskBuilder addTaskCost(TaskCost taskCost) {
        this.entity.addTaskCost(taskCost);
        return this;
    }

    public TaskBuilder addCheckList(Checklist checkList) {
        this.entity.addCheckList(checkList);
        return this;
    }

    public TaskBuilder addSignedBy(User user) {
        this.entity.addSignedBy(user);
        return this;
    }

    public TaskBuilder addChild(Task task) {
        this.entity.addChild(task);
        return this;
    }

    public TaskBuilder addNotification(Notification notification) {
        this.entity.addNotification(notification);
        return this;
    }


	
	public static TaskBuilder createTaskBuilder(Long id) {
		return new TaskBuilder(id);
	}

	public static TaskBuilder createTaskBuilder() {
		return new TaskBuilder();
	}
}
