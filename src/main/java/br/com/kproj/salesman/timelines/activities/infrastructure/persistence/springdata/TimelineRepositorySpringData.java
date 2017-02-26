package br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.delivery.DeliveryEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelineEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("timelineRepositorySpringDataTimelineActivitiesModule")
public interface TimelineRepositorySpringData extends BaseRepositoryLegacy<TimelineEntity, Long> {


}
