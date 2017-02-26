package br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.AppFileEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("mediaRepositorySpringDataTimelineActivitiesModule")
public interface MediaRepositorySpringData extends BaseRepositoryLegacy<AppFileEntity, Long> {



}
