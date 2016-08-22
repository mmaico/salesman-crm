package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;

public class LogActivityBuilder extends AbstractBuilder<LogActivity>  {

	public LogActivityBuilder() {
		this.entity = new LogActivity();
	}

	public LogActivityBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

	public LogActivityBuilder withType(LogActivityTypeEnum type) {
		this.entity.setType(type);
		return this;
	}

	public LogActivityBuilder withDescription(String description) {
		this.entity.setDescription(description);
		return this;
	}

	public LogActivityBuilder withUser(UserEntity user) {
		this.entity.setUser(user);
		return this;
	}



	public static LogActivityBuilder createLogActivity(Long id) {
		return new LogActivityBuilder(id);
	}

	public static LogActivityBuilder createLogActivity() {
		return new LogActivityBuilder();
	}
	


}
