package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityChecklistRepositorySpringdataActivityModule")
public interface ActivityChecklistRepositorySpringdata extends BaseRepositoryLegacy<ActivityChecklistEntity, Long> {

    @Query("SELECT a FROM ActivityChecklistEntity AS a WHERE a.activity.id = :activityid")
    List<ActivityChecklistEntity> findByActivity(@Param("activityid") Long activityId);
}
