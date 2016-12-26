package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContactRepository;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Contact;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarContactActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.CalendarActiityToActivity;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityContactEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.trex.clone.BusinessModelClone.from;

@Repository("activityRepositoryHibernateCalendarModule")
public class ActivityContactRepositoryHibernate extends BaseRespositoryImpl<ActivityContact, CalendarActivityContactEntity> implements ActivityContactRepository {


    private CalendarContactActivityRepository repository;
    private CalendarActivityRepository activityRepository;
    private CalendarActiityToActivity converter;

    @Autowired
    public ActivityContactRepositoryHibernate(CalendarContactActivityRepository repository,
                                              CalendarActiityToActivity converter,
                                              CalendarActivityRepository activityRepository) {
        this.repository = repository;
        this.converter = converter;
        this.activityRepository = activityRepository;
    }

    @Override
    public Optional<ActivityContact> makeSpecialization(ActivityContact activityContact) {
        CalendarActivityEntity activityEntity = activityRepository.findOne(activityContact.getId());

        CalendarActivityContactEntity contactEntity = new CalendarActivityContactEntity();
        contactEntity.setId(activityContact.getId());
        contactEntity.setContact(new ContactEntity(activityContact.getContact().getId()));
        contactEntity.setActivity(activityEntity);
        activityEntity.setRepresent(CalendarActivityType.CONTACT);
        CalendarActivityContactEntity contactEntitySaved = repository.save(contactEntity);

        return Optional.of(getConverter().convert(contactEntitySaved));
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivityContactEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivityContactEntity, ActivityContact> getConverter() {
        return ((activityContactEntity, args) -> {
            CalendarActivityEntity activityEntity = activityContactEntity.getActivity();
            Activity activity = converter.convert(activityEntity);
            ActivityContact activityContact = from(activity).convertTo(ActivityContact.class);
            activityContact.setContact(new Contact(activityEntity.getId()));

            return activityContact;
        });
    }
}
