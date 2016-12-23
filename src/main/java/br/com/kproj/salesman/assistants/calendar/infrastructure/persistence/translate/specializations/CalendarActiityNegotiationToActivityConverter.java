package br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.specializations;


import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.Represent;
import br.com.kproj.salesman.assistants.calendar.domain.model.relations.Negotiation;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarBusinessProposalActivityRepository;
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.translate.ActivityEntityHolderConverter;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityBusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarActiityNegotiationToActivityConverter implements Converter<CalendarActivityEntity, ActivityNegotiation> {


    private CalendarBusinessProposalActivityRepository repository;
    private ActivityEntityHolderConverter converter;


    @Autowired
    public CalendarActiityNegotiationToActivityConverter(CalendarBusinessProposalActivityRepository repository, ActivityEntityHolderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public ActivityNegotiation convert(CalendarActivityEntity activityEntity, Object... args) {
        CalendarActivityBusinessProposalEntity entity = repository.findOne(activityEntity.getId());

        ActivityNegotiation activity = new ActivityNegotiation();
        activity.setRepresent(Represent.NEGOTIATION);
        converter.merge(activity, activityEntity);
        activity.setNegotiation(new Negotiation(entity.getProposal().getId()));

        return activity;
    }

}
