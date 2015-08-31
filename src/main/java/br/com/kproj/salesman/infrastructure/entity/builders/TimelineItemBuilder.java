package br.com.kproj.salesman.infrastructure.entity.builders;


import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineItem;

import java.util.Date;
import java.util.List;

public class TimelineItemBuilder extends AbstractBuilder<TimelineItem>  {

	public TimelineItemBuilder() {
		this.entity = new TimelineItem();
	}

	public TimelineItemBuilder(Long id) {
		this();
		this.entity.setId(id);
	}
	
	public TimelineItemBuilder withCreateDate(Date date) {
		this.entity.setCreation(date);
		return this;
	}

    public TimelineItemBuilder withDescription(String description) {
        this.entity.setDescription(description);
        return this;
    }

    public TimelineItemBuilder withFiles(List<AppFile> files) {
        this.entity.setFiles(files);
        return this;
    }

    public static TimelineItemBuilder createItem(Long id) {
		return new TimelineItemBuilder(id);
	}

	public static TimelineItemBuilder createItem() {
		return new TimelineItemBuilder();
	}
}