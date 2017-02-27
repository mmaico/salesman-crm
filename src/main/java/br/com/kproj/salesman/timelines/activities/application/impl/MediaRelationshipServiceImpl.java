package br.com.kproj.salesman.timelines.activities.application.impl;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.timelines.activities.application.MediaRelationshipFacade;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationshipRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationshipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("mediaRelationshipyServiceImplTimelineActivitiesModule")
public class MediaRelationshipServiceImpl extends BaseModelServiceImpl<MediaRelationship> implements MediaRelationshipFacade {


    private MediaRelationshipRepository repository;

    private MediaRelationshipValidator validator;


    @Autowired
    public MediaRelationshipServiceImpl(MediaRelationshipValidator validator, MediaRelationshipRepository repository) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Optional<MediaRelationship> register(MediaRelationship relationship) {
        validator.checkRules(relationship);
        return Optional.of(repository.register(relationship));
    }

    @Override
    public void delete(Long relationId) {
        repository.delete(relationId);
    }

    @Override
    public Collection<MediaRelationship> findAll(Long activityId) {
        return repository.findAll(activityId);
    }

    @Override
    public BaseRepository<MediaRelationship, Long> getRepository() {
        return repository;
    }
}
