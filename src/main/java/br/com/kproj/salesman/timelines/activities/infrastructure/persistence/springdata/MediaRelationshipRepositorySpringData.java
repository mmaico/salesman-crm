package br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.timeline.items.relationship.ActivityMediaRelationshipEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MediaRelationshipRepositorySpringData extends BaseRepositoryLegacy<ActivityMediaRelationshipEntity, Long> {


    @Query("SELECT amre FROM ActivityMediaRelationshipEntity AS amre WHERE amre.activity.id = :activityId")
    Collection<ActivityMediaRelationshipEntity> findAll(@Param("activityId") Long activityId);

    @Query("SELECT CASE WHEN count(item) > 0 THEN true ELSE false END FROM ActivityMediaRelationshipEntity AS item " +
            " WHERE item.media.id = :mediaId AND item.activity.id = :activityId")
    Boolean alreadyExists(@Param("activityId") Long activityId, @Param("mediaId") Long mediaId);

}
