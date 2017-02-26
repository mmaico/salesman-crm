package br.com.kproj.salesman.timelines.timeline.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("timelineRepositorySpringdataModuleTimelines")
public interface TimelineRepositorySpringdata extends BaseRepositoryLegacy<TimelineEntity, Long> {




}
