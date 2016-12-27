package br.com.kproj.salesman.assistants.activities.infrastructure.persistence.translate;

import br.com.kproj.salesman.assistants.activities.domain.model.personal.Activity;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Represent;
import br.com.kproj.salesman.assistants.activities.domain.model.personal.Status;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Assigner;
import br.com.kproj.salesman.assistants.activities.domain.model.user.Owner;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivityEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;



@Component
public class ActivityEntityToActivityConverter implements Converter<PersonalActivityEntity, Activity> {


    @Override
    public Activity convert(PersonalActivityEntity activityEntity, Object... args) {

        Activity activity = new Activity();
        activity.setId(activityEntity.getId());
        activity.setDeadline(activityEntity.getDeadline());
        activity.setTitle(activityEntity.getTitle());
        activity.setDescription(activityEntity.getDescription());
        activity.setOwner(new Owner(activityEntity.getOwner().getId()));
        activity.setStatus(Status.valueOf(activityEntity.getStatus().name()));

        Optional<Represent> represent = activityEntity.getType() == null
                ? Optional.of(Represent.NO_REPRESENT)
                : Optional.ofNullable(Represent.valueOf(activityEntity.getType().name()));

        activity.setRepresent(represent.orElse(Represent.NO_REPRESENT));

        if (activityEntity.getAssigner() != null) {
            activity.setAssigner(new Assigner(activityEntity.getAssigner().getId()));
        }

        return activity;
    }
}
