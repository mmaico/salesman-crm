package br.com.kproj.salesman.timelines.activities.application;


import br.com.kproj.salesman.infrastructure.service.ModelFacade;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;

import java.util.Collection;
import java.util.Optional;

public interface MediaRelationshipFacade extends ModelFacade<MediaRelationship> {

    Optional<MediaRelationship> register(MediaRelationship newRelationship);

    void delete(MediaRelationship relationship);

    Collection<MediaRelationship> findAll(MediaRelationship relationship);

}
