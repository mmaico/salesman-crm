package br.com.kproj.salesman.timelines.activities.domain.model.media;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;

import java.util.Collection;

public interface MediaRelationshipRepository extends BaseRepository<MediaRelationship, Long> {

    Boolean alreadyExists(Media media, Activity activity);

    void delete(Long id);

    Collection<MediaRelationship> findAll(Long activityId);

    MediaRelationship register(MediaRelationship relationship);

}
