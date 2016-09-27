package br.com.kproj.salesman.notifications.logview.domain.model.view;


import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;
import br.com.kproj.salesman.notifications.logview.domain.model.user.User;

import java.util.Date;

public class ViewInfoBuilder extends AbstractBuilder<ViewInfo>  {

	public ViewInfoBuilder() {
		this.entity = new ViewInfo();
	}

	public ViewInfoBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public ViewInfoBuilder withUser(User user) {
		this.entity.setUser(user);
		return this;
	}

	public ViewInfoBuilder visualizationNow() {
		this.entity.setVisualization(new Date());
		return this;
	}

	public ViewInfoBuilder visualization(Date visualization) {
		this.entity.setVisualization(visualization);
		return this;
	}

	public ViewInfoBuilder withType(NotificationType type) {
		this.entity.setType(type);
		return this;
	}

	public static ViewInfoBuilder createView(Long id) {
		return new ViewInfoBuilder(id);
	}

	public static ViewInfoBuilder createView() {
		return new ViewInfoBuilder();
	}

}
