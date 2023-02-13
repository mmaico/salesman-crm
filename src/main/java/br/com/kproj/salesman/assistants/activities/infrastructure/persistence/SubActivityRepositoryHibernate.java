package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.rootactivity.RootActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.subactivity.SubActivityRepository;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.PersonalAcvitityRepository;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.SubAcvitityEntityRepositorySpringData;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.translate.ActivityEntityToActivityConverter;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.RootPersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.SubPersonalActvityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import com.github.mmaico.clone.BusinessModelClone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class SubActivityRepositoryHibernate extends BaseRespositoryImpl<SubActivity, SubPersonalActvityEntity> implements SubActivityRepository {

    @Autowired
    private SubAcvitityEntityRepositorySpringData repository;

    @Autowired
    private PersonalAcvitityRepository acvitityRepository;

    @Autowired
    private ActivityEntityToActivityConverter activityConverter;

    @Override
    public BaseRepositoryLegacy<SubPersonalActvityEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<SubPersonalActvityEntity, SubActivity> getConverter() {
        return (subEntity, args) -> {
            Activity activity = activityConverter.convert(subEntity.getActivity());

            SubActivity subActivity = BusinessModelClone.from(activity).convertTo(SubActivity.class);
            RootPersonalActivityEntity parent = subEntity.getParent();

            subActivity.setParent(new RootActivity(parent.getId()));

            return subActivity;
        };
    }


    @Override
    public Optional<SubActivity> register(SubActivity activity) {
        PersonalActivityEntity activityEntity = acvitityRepository.findOne(activity.getId());

        SubPersonalActvityEntity entity = new SubPersonalActvityEntity(activityEntity.getId());
        entity.setActivity(activityEntity);
        entity.setParent(new RootPersonalActivityEntity(activity.getParent().getId()));
        activityEntity.setType(ActivityTypeEntity.SUBACTIVITY);

        SubPersonalActvityEntity subSaved = repository.save(entity);
        return Optional.of(getConverter().convert(subSaved));
    }

    @Override
    public Page<SubActivity> findAll(RootActivity rootActivity, Pageable pageable) {
        Page<SubPersonalActvityEntity> result = repository.findAll(rootActivity.getId(), pageable);
        List<SubActivity> subActivities = result.getContent().stream().map(entity -> getConverter().convert(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(subActivities, pageable, result.getTotalElements());
    }
}
