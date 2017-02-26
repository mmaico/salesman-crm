package br.com.kproj.salesman.assistants.activities.infrastructure.persistence;

import br.com.kproj.salesman.assistants.activities.domain.model.checklist.Checklist;
import br.com.kproj.salesman.assistants.activities.domain.model.checklist.ChecklistRepository;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.infrastructure.persistence.springdata.ActivityChecklistRepositorySpringdata;
import br.com.kproj.salesman.infrastructure.entity.activities.ActivityChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trex.clone.BusinessModelClone.from;


@Repository("checklistRepositoryHibernateActivitiesModule")
public class ChecklistRepositoryHibernate extends BaseRespositoryImpl<Checklist, ActivityChecklistEntity> implements ChecklistRepository {


    private ActivityChecklistRepositorySpringdata checklistRepository;

    @Autowired
    public ChecklistRepositoryHibernate (ActivityChecklistRepositorySpringdata checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    @Override
    public Checklist update(Checklist checklist) {
        ActivityChecklistEntity checklistEntity = checklistRepository.findOne(checklist.getId());
        from(checklist).merge(checklistEntity);

        checklistRepository.save(checklistEntity);
        return getConverter().convert(checklistEntity);
    }

    @Override
    public Collection<Checklist> findAll(Activity activity) {
        List<ActivityChecklistEntity> result = checklistRepository.findByActivity(activity.getId());

        return result.stream().map(activityChecklistEntity -> getConverter()
                .convert(activityChecklistEntity)).collect(Collectors.toList());
    }

    @Override
    public Optional<Checklist> newCheckList(Checklist checklist, Activity activity) {
        ActivityChecklistEntity activityEntity = new ActivityChecklistEntity();
        activityEntity.setName(checklist.getName());
        activityEntity.setDone(Boolean.FALSE);
        activityEntity.setActivity(new PersonalActivityEntity(activity.getId()));

        ActivityChecklistEntity result = checklistRepository.save(activityEntity);

        return Optional.of(getConverter().convert(result));
    }

    @Override
    public BaseRepositoryLegacy<ActivityChecklistEntity, Long> getRepository() {
        return checklistRepository;
    }

    @Override
    public Converter<ActivityChecklistEntity, Checklist> getConverter() {
        return ((checklistEntity, args) -> {
            Checklist checklist = new Checklist();
            checklist.setDone(checklistEntity.getDone());
            checklist.setName(checklistEntity.getName());
            checklist.setId(checklistEntity.getId());
            checklist.setActivity(new Activity(checklistEntity.getActivity().getId()));

            return checklist;
        });
    }


}
