package br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepositorySpringData extends BaseRepositoryLegacy<TimelineActivity, Long> {


    @Query("SELECT ta FROM TimelineActivity AS ta WHERE ta.timeline.id = :timelineId")
    Page<TimelineActivity> findAll(@Param("timelineId") Long timelineId, Pageable pageable);



}
