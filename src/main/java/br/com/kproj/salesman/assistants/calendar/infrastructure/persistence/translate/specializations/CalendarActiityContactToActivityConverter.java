package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.Represent;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Contact;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarContactActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.ActivityEntityHolderConverter;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityContactEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarActiityContactToActivityConverter implements Converter<CalendarActivityEntity, ActivityContact> {


    private CalendarContactActivityRepository repository;
    private ActivityEntityHolderConverter converter;


    @Autowired
    public CalendarActiityContactToActivityConverter(CalendarContactActivityRepository repository, ActivityEntityHolderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public ActivityContact convert(CalendarActivityEntity activityEntity, Object... args) {
        CalendarActivityContactEntity activityContactEntity = repository.findOne(activityEntity.getId());

        ActivityContact activity = new ActivityContact();
        activity.setRepresent(Represent.CONTACT);
        converter.merge(activity, activityEntity);
        activity.setContact(new Contact(activityContactEntity.getContact().getId()));

        return activity;
    }

}
