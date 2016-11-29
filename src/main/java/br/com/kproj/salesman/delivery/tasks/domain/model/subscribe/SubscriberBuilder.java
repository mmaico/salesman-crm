package br.com.kproj.salesman.delivery.tasks.domain.model.subscribe;


import br.com.kproj.salesman.delivery.tasks.domain.model.tasks.Task;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class SubscriberBuilder extends AbstractBuilder<Subscriber>  {

	public SubscriberBuilder() {
		this.entity = new Subscriber();
	}

	public SubscriberBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public SubscriberBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}

	public SubscriberBuilder withTask(Task task) {
		this.entity.setTask(task);
		return this;
	}

	public static SubscriberBuilder createSubscriber(Long id) {
		return new SubscriberBuilder(id);
	}

	public static SubscriberBuilder createSubscriber() {
		return new SubscriberBuilder();
	}
}
