package br.com.kproj.salesman.timelines.timeline.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.timelines.timeline.domain.model.activities.Activity;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.timeline.domain.model.timeline.TimelineRepository;
import br.com.kproj.salesman.timelines.timeline.infrastructure.persistence.springdata.TimelineRepositorySpringdata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.kproj.salesman.timelines.timeline.domain.model.timeline.TimelineBuilder.createTimeline;


@Repository("timelineRepositoryHibernateTimelinesModule")
public class TimelineRepositoryHibernate extends BaseRespositoryImpl<Timeline, TimelineEntity> implements TimelineRepository {

    private TimelineRepositorySpringdata timelineEntityRepository;

    @Autowired
    public TimelineRepositoryHibernate(TimelineRepositorySpringdata timelineEntityRepository) {
        this.timelineEntityRepository = timelineEntityRepository;
    }

    @Override
    public Timeline newTimeline() {
        TimelineEntity timelineEntity = timelineEntityRepository.save(new TimelineEntity());

        return getConverter().convert(timelineEntity);
    }

    @Override
    public BaseRepositoryLegacy<TimelineEntity, Long> getRepository() {
        return timelineEntityRepository;
    }

    @Override
    public Converter<TimelineEntity, Timeline> getConverter() {
        return (timelineEntity, args) -> {

            List<Activity> activities = timelineEntity.getActivities().stream()
                    .map(entity -> new Activity(entity.getId()))
                    .collect(Collectors.toList());

            return createTimeline(timelineEntity.getId()).withActivities(activities).build();
        } ;
    }

}
