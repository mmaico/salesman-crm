package br.com.kproj.salesman.assistants.activities.domain.model.checklist;



import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.infrastructure.entity.builders.AbstractBuilder;

public class ChecklistBuilder extends AbstractBuilder<Checklist>  {

	public ChecklistBuilder() {
		this.entity = new Checklist();
	}

	public ChecklistBuilder(Long id) {
		this();
		this.entity.setId(id);
	}

    public ChecklistBuilder withName(String name) {
        this.entity.setName(name);
        return this;
    }

    public ChecklistBuilder withDone(Boolean isDone) {
        this.entity.setDone(isDone);
        return this;
    }

    public ChecklistBuilder withActivity(Activity activity) {
        this.entity.setActivity(activity);
        return this;
    }

	public static ChecklistBuilder createChecklist(Long id) {
		return new ChecklistBuilder(id);
	}

	public static ChecklistBuilder createChecklist() {
		return new ChecklistBuilder();
	}
}
