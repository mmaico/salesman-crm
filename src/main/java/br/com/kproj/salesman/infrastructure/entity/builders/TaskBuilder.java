package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatusEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;

import java.util.Date;

public class TaskBuilder extends AbstractBuilder<TaskEntity>  {

	public TaskBuilder() {
		this.entity = new TaskEntity();
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

    public TaskBuilder withStatus(TaskStatusEntity status) {
        this.entity.setStatus(status);
        return this;
    }

//    public TaskBuilder withParent(TaskEntity parent) {
//        this.entity.setParent(parent);
//        return this;
//    }
//
//    public TaskBuilder withSalesOrder(SalesOrderEntity order) {
//        this.entity.setSalesOrder(order);
//        return this;
//    }
//
//    public TaskBuilder withRegion(OperationRegionEntity region) {
//        this.entity.setRegion(region);
//        return this;
//    }
//
//    public TaskBuilder withSignedBy(List<UserEntity> users) {
//        this.entity.setSignedBy(users); ;
//        return this;
//    }
//
//    public TaskBuilder addTaskCost(TaskCost taskCost) {
//        this.entity.addTaskCost(taskCost);
//        return this;
//    }
//
//    public TaskBuilder addCheckList(ChecklistEntity checkList) {
//        this.entity.addCheckList(checkList);
//        return this;
//    }
//
//    public TaskBuilder addSignedBy(UserEntity user) {
//        this.entity.addSignedBy(user);
//        return this;
//    }
//
//    public TaskBuilder addChild(TaskEntity taskEntity) {
//        this.entity.addChild(taskEntity);
//        return this;
//    }
//
//    public TaskBuilder addNotification(ScheduleTriggerNotification notification) {
//        this.entity.addTriggerNotification(notification);
//        return this;
//    }

	
	public static TaskBuilder createTaskBuilder(Long id) {
		return new TaskBuilder(id);
	}

	public static TaskBuilder createTaskBuilder() {
		return new TaskBuilder();
	}
}
