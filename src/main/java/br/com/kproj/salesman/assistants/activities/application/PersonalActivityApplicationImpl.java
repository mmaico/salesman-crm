package br.com.kproj.salesman.assistants.activities.application;

import br.com.kproj.salesman.assistants.activities.domain.PersonalActivityDomainService;
import br.com.kproj.salesman.assistants.activities.infrastructure.PersonalAcvitityRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.HandlerErrors.hasErrors;
import static br.com.kproj.salesman.infrastructure.helpers.ModelHelper.isNull;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@Service
public class PersonalActivityApplicationImpl extends BaseModelServiceLegacyImpl<PersonalActivity> implements PersonalActivityApplication {

	@Autowired
	private PersonalAcvitityRepository repository;

    @Autowired
    private PersonalActivityDomainService service;

    @Override
    public PersonalActivity register(PersonalActivity activity) {
        PersonalActivity activitySaved;

        if (!activity.isNew()) {
            activity.setStatus(PersonalAcvitityStatus.WAITING);
            activitySaved =  super.save(activity, service);
        } else {
            service.checkBusinessRulesFor(activity);
            activitySaved = super.save(activity);
        }

        if (activity.hasValidParent()) {
            Optional<PersonalActivity> parentLoaded = repository.getOne(activity.getParent().getId());
            if (parentLoaded.isPresent()) {
                parentLoaded.get().addChild(activitySaved);
            }
        }

        return activitySaved;
    }

    @Override
    public PersonalActivity registerSubtask(PersonalActivity parent, PersonalActivity activityChild) {


        Optional<PersonalActivity> activityParentLoaded = repository.getOne(parent.getId());

        if (!activityParentLoaded.isPresent()) {
            throw new ValidationException(Sets.newHashSet("activity.child.with.invalid.parent"));
        }

        activityChild.setParent(activityParentLoaded.get());

        return register(activityChild);
    }

    @Override
    public void changeStatus(PersonalActivity activity, UserEntity userChange) {

        PersonalActivity activityLoaded = repository.findOne(activity.getId());

        hasErrors(isNull(activityLoaded) ? newHashSet("activity.not.found") : emptySet())
                .throwing(ValidationException.class);

        activityLoaded.setStatus(activity.getStatus());
        repository.save(activityLoaded);
    }


    public BaseRepositoryLegacy<PersonalActivity, Long> getRepository() {
        return repository;
    }
}
