package br.com.kproj.salesman.timelines.activities.application.validators;

import br.com.kproj.salesman.infrastructure.validators.CheckRule;
import br.com.kproj.salesman.infrastructure.validators.RuleKey;
import br.com.kproj.salesman.infrastructure.validators.RulesExecute;
import br.com.kproj.salesman.timelines.activities.domain.model.activities.activity.ActivityRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationship;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationshipRepository;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRelationshipValidator;
import br.com.kproj.salesman.timelines.activities.domain.model.media.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.timelines.activities.application.validators.descriptions.MediaRelationshipRulesDescription.*;

@Component("mediaRelationshipBusinessRulesTimelineActivitiesModule")
public class MediaRelationshipBusinessRules implements MediaRelationshipValidator {

    private MediaRepository repository;

    private ActivityRepository activityRepository;

    private MediaRelationshipRepository relationshipRepository;


    @Autowired
    public MediaRelationshipBusinessRules(MediaRepository repository, ActivityRepository activityRepository, MediaRelationshipRepository relationshipRepository) {
        this.repository = repository;
        this.activityRepository = activityRepository;
        this.relationshipRepository = relationshipRepository;
    }

    private Map<RuleKey, CheckRule<MediaRelationship>> rules = new HashMap<>();
    {
        rules.put(ruleInvalidActvity(), relationship ->
                relationship.getActivity() == null || relationship.getActivity().isNew()
                        || !activityRepository.findOne(relationship.getActivity().getId()).isPresent());

        rules.put(ruleInvalidMedia(), relationship ->
                relationship.getMedia() == null || relationship.getMedia().isNew() || !repository.findOne(relationship.getMedia().getId()).isPresent());

        rules.put(ruleRelationshipAlreadyExists(), relationship ->
                relationshipRepository.alreadyExists(relationship.getMedia(), relationship.getActivity())
        );
    }

    @Override
    public void checkRules(MediaRelationship relationship) {
        RulesExecute.runRules(rules, relationship);
    }

}
