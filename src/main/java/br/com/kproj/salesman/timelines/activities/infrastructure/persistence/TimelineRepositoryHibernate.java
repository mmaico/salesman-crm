package br.com.kproj.salesman.timelines.activities.infrastructure.persistence;


import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.Timeline;
import br.com.kproj.salesman.timelines.activities.domain.model.timeline.TimelineRepository;
import br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata.TimelineRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("timelineRepositoryHibernateTimelineActivitiesModule")
public class TimelineRepositoryHibernate extends BaseRespositoryImpl<Timeline, TimelineEntity> implements TimelineRepository {


    @Autowired
    private TimelineRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<TimelineEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<TimelineEntity, Timeline> getConverter() {
        return ((entity, args) -> new Timeline(entity.getId()));
    }
}
