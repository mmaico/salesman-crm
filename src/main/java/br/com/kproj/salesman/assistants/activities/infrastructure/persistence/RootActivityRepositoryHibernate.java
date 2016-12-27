package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivityRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.PersonalAcvitityRepository;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.RootAcvitityEntityRepositorySpringData;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.translate.ActivityEntityToActivityConverter;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.RootPersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.trex.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class RootActivityRepositoryHibernate extends BaseRespositoryImpl<RootActivity, RootPersonalActivityEntity> implements RootActivityRepository {

    @Autowired
    private RootAcvitityEntityRepositorySpringData repository;

    @Autowired
    private PersonalAcvitityRepository acvitityRepository;

    @Autowired
    private ActivityEntityToActivityConverter activityConverter;

    @Override
    public BaseRepositoryLegacy<RootPersonalActivityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<RootPersonalActivityEntity, RootActivity> getConverter() {
        return (rootActivityEntity, args) -> {
            Activity activity = activityConverter.convert(rootActivityEntity.getActivity());
            return BusinessModelClone.from(activity).convertTo(RootActivity.class);
        };
    }

    @Override
    public Iterable<RootActivity> findAll(Owner owner, Pageable pageable) {
        Page<RootPersonalActivityEntity> result = repository.findAll(owner.getId(), pageable);

        List<RootActivity> rootActivities = result.getContent().stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(rootActivities, pageable, result.getTotalElements());
    }

    @Override
    public Optional<RootActivity> register(RootActivity activity) {

        RootPersonalActivityEntity entity = new RootPersonalActivityEntity();
        entity.setId(activity.getId());

        PersonalActivityEntity personalActivityEntity = acvitityRepository.findOne(activity.getId());
        entity.setActivity(personalActivityEntity);
        personalActivityEntity.setType(ActivityTypeEntity.ROOTACTIVITY);
        RootPersonalActivityEntity rootActivitySaved = repository.save(entity);

        return Optional.of(getConverter().convert(rootActivitySaved));
    }
}
