package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.service.ModelService;

public interface TimelineApplication extends ModelService<Timeline> {



    <T extends TimelinePresent> Timeline register(T entity);
}

