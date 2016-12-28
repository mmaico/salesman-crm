package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.ActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.PersonalAcvitityRepository;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.translate.ActivityEntityToActivityConverter;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonalAcvitityStatus;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository
public class ActivityRepositoryHibernate extends BaseRespositoryImpl<Activity, PersonalActivityEntity> implements ActivityRepository {

    @Autowired
    private PersonalAcvitityRepository repository;

    @Autowired
    private ActivityEntityToActivityConverter converter;


    @Override
    public Optional<Activity> save(Activity activity) {

        PersonalActivityEntity personalActivityEntity = from(activity).convertTo(PersonalActivityEntity.class);

        personalActivityEntity.setStatus(PersonalAcvitityStatus.WAITING);
        PersonalActivityEntity activitySaved = repository.save(personalActivityEntity);
        return Optional.of(getConverter().convert(activitySaved));
    }

    @Override
    public Activity update(Activity activity) {
        PersonalActivityEntity activityEntity = repository.findOne(activity.getId());
        from(activity).merge(activityEntity);
        repository.save(activityEntity);

        return getConverter().convert(activityEntity);
    }

    @Override
    public Iterable<Activity> findAll(Owner owner, Pageable pageable) {
        Page<PersonalActivityEntity> result = repository.findAll(new UserEntity(owner.getId()), pageable);
        List<Activity> activities = result.getContent().stream().map(entity -> getConverter().convert(entity)).collect(Collectors.toList());

        return new PageImpl<>(activities, pageable, result.getTotalElements());
    }


    @Override
    public BaseRepositoryLegacy<PersonalActivityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<PersonalActivityEntity, Activity> getConverter() {
        return converter;
    }


}
