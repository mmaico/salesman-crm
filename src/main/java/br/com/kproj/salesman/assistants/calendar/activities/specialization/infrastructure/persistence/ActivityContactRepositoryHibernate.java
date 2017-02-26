package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityContactRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.contact.Contact;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.CalendarContactActivityRepositorySpringData;
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

@Repository("activityContactRepositoryHibernateCalendarModule")
public class ActivityContactRepositoryHibernate extends BaseRespositoryImpl<ActivityContact, CalendarActivityContactEntity> implements ActivityContactRepository {


    private CalendarContactActivityRepositorySpringData repository;
    private CalendarActivityRepository activityRepository;

    @Autowired
    public ActivityContactRepositoryHibernate(CalendarContactActivityRepositorySpringData repository,
                                              CalendarActivityRepository activityRepository) {
        this.repository = repository;
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

            ActivityContact activityContact = new ActivityContact();
            activityContact.setId(activityContactEntity.getId());
            activityContact.setContact(new Contact(activityContactEntity.getContact().getId()));

            return activityContact;
        });
    }
}
