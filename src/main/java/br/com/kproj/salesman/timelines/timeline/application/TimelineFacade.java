package br.com.kproj.salesman.timelines.timeline.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;

import java.util.Optional;

public interface TimelineFacade extends ModelFacade<Timeline> {

    Timeline newTimeline();

}
