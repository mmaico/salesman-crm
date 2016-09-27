package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.*;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.PersonalAcvitityRepository;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.helpers.ReflectionsHelper.copyProperties;
import static com.trex.clone.BusinessModelClone.from;


@Repository
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, PersonalActivityEntity> implements ActivityRepository {

    @Autowired
    private PersonalAcvitityRepository repository;


    @Override
    public Optional<Activity> save(Activity activity) {

        PersonalActivityEntity personalActivityEntity = from(activity).convertTo(PersonalActivityEntity.class);

        if (activity.isNew()) {
            personalActivityEntity.setStatus(PersonalAcvitityStatus.WAITING);
            PersonalActivityEntity activitySaved = repository.save(personalActivityEntity);
            return Optional.of(getConverter().convert(activitySaved));
        } else {
            PersonalActivityEntity activitySaved = repository.save(personalActivityEntity);
            return Optional.of(getConverter().convert(activitySaved));
        }
    }

    public Optional<SubActivity> save(SubActivity subActivity) {

        Activity parent = subActivity.getParent();
        PersonalActivityEntity activityParent = repository.findOne(parent.getId());

        PersonalActivityEntity personalActivity = from(subActivity).convertTo(PersonalActivityEntity.class);

        PersonalActivityEntity personalActivitySaved = repository.save(personalActivity);
        activityParent.addChild(personalActivitySaved);

        Activity activitySaved = getConverter().convert(personalActivitySaved);
        SubActivity subactivity = new SubActivity();

        copyProperties(subactivity, activitySaved);
        subactivity.setParent(parent);

        return Optional.of(subactivity);
    }

    public Activities findAll(Owner owner) {
        Collection<PersonalActivityEntity> result = repository.findAll(new UserEntity(owner.getId()));
        Activities activities = Activities.createActivities(Lists.newArrayList());

        result.stream().forEach(entity -> {
            Activity activity = getConverter().convert(entity);
            activities.add(activity);
        });

        return activities;
    }

    @Override
    public void changeStatus(Activity activity, Status newStatus) {
        PersonalActivityEntity activityEntity = repository.findOne(activity.getId());

        PersonalAcvitityStatus newStatusEntity = PersonalAcvitityStatus.get(newStatus.name());
        activityEntity.setStatus(newStatusEntity);
    }


    @Override
    public BaseRepositoryLegacy<PersonalActivityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<PersonalActivityEntity, Activity> getConverter() {
        return null;
    }
}
