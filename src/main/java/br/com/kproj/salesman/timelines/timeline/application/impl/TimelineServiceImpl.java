package br.com.kproj.salesman.timelines.timeline.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.timelines.timeline.application.TimelineFacade;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("timelineServiceImplModuleTimelines")
public class TimelineServiceImpl extends BaseModelServiceImpl<Timeline> implements TimelineFacade {

    @Autowired
    private TimelineRepository repository;


    public Timeline newTimeline() {
        return repository.newTimeline();
    }

    @Override
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
