package br.com.kproj.salesman.notifications.approval.domain.model.notification;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.notifications.approval.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.notifications.approval.domain.model.user.Receiver;

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
		this.entity.setCreation(new Date());
		return this;
	}

	public NotificationBuilder withCreation(Date date) {
		this.entity.setCreation(date);
		return this;
	}

	public NotificationBuilder withNegotiation(Negotiation negotiation) {
		this.entity.setNegotiation(negotiation);
		return this;
	}

	public static NotificationBuilder createView(Long id) {
		return new NotificationBuilder(id);
	}

	public static NotificationBuilder createView() {
		return new NotificationBuilder();
	}

}
