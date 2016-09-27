package br.com.kproj.salesman.auditing.delivery.domain.model.audit;


import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;

import br.com.kproj.salesman.auditing.delivery.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

import java.util.Date;

public class AuditingBuilder extends AbstractBuilder<Auditing>  {

	public AuditingBuilder() {
		this.entity = new Auditing();
	}

	public AuditingBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public AuditingBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}

	public AuditingBuilder withData(String data) {
		this.entity.setData(data);
		return this;
	}

	public AuditingBuilder currentDate() {
		this.entity.setLastUpdate(new Date());
		return this;
	}

	public AuditingBuilder withLastUpdate(Date lastUpdate) {
		this.entity.setLastUpdate(lastUpdate);
		return this;
	}

	public AuditingBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}

	public static AuditingBuilder createNewAudit() {
		return new AuditingBuilder();
	}

	public static AuditingBuilder createAudit(Long id) {
		return new AuditingBuilder(id);
	}
}
