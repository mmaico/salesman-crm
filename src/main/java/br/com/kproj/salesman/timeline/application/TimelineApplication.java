package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.service.ModelLegacyService;

public interface TimelineApplication extends ModelLegacyService<Timeline> {



    <T extends TimelinePresent> Timeline register(T entity);
}

