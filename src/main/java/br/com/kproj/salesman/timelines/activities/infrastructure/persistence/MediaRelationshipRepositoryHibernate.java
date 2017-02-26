package br.com.kproj.salesman.timelines.activities.infrastructure.persistence;

import br.com.kproj.salesman.infrastructure.entity.timeline.items.relationship.ActivityMediaRelationshipEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.Activity;
import br.com.kproj.salesman.timelines.activities.domain.model.media.Media;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationshipRepository;
import br.com.kproj.salesman.timelines.activities.infrastructure.persistence.springdata.MediaRelationshipRepositorySpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;


@Repository("mediaRelationshipRepositoryHibernateTimelineActivitiesModule")
public class MediaRelationshipRepositoryHibernate extends BaseRespositoryImpl<MediaRelationship, ActivityMediaRelationshipEntity> implements MediaRelationshipRepository {


    private MediaRelationshipRepositorySpringData repository;

    @Autowired
    public MediaRelationshipRepositoryHibernate(MediaRelationshipRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public Boolean alreadyExists(Media media, Activity activity) {
        return repository.alreadyExists(activity.getId(), media.getId());
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Collection<MediaRelationship> findAll(Long activityId) {
        Collection<ActivityMediaRelationshipEntity> result = repository.findAll(activityId);
        return result.stream().map(entity -> getConverter()
                .convert(entity)).collect(Collectors.toList());
    }

    @Override
    public Converter<ActivityMediaRelationshipEntity, MediaRelationship> getConverter() {
        return ((relationshipEntity, args) -> {
            MediaRelationship mediaRelationship = new MediaRelationship();
            mediaRelationship.setId(relationshipEntity.getId());
            mediaRelationship.setActivity(new Activity(relationshipEntity.getActivity().getId()));
            mediaRelationship.setMedia(new Media(relationshipEntity.getMedia().getId()));

            return mediaRelationship;
        });
    }

    @Override
    public BaseRepositoryLegacy<ActivityMediaRelationshipEntity, Long> getRepository() {
        return repository;
    }

}
