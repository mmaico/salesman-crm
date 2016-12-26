package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence;


import br.com.kproj.salesman.assistants.calendar.activities.activity.infrastructure.persistence.springdata.CalendarActivityRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.activity.ActivityNegotiationRepository;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.domain.model.negotiation.Negotiation;
import br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata.CalendarBusinessProposalActivityRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityBusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityType;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("activityNegotiationRepositoryHibernateCalendarModule")
public class ActivityNegotiationRepositoryHibernate extends BaseRespositoryImpl<ActivityNegotiation, CalendarActivityBusinessProposalEntity> implements ActivityNegotiationRepository {


    private CalendarBusinessProposalActivityRepositorySpringData repository;
    private CalendarActivityRepository activityRepository;

    @Autowired
    public ActivityNegotiationRepositoryHibernate(CalendarBusinessProposalActivityRepositorySpringData repository,
                                                  CalendarActivityRepository activityRepository) {
        this.repository = repository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Optional<ActivityNegotiation> makeSpecialization(ActivityNegotiation activityNegotiation) {
        CalendarActivityEntity activityEntity = activityRepository.findOne(activityNegotiation.getId());

        CalendarActivityBusinessProposalEntity businessProposalEntity = new CalendarActivityBusinessProposalEntity();
        businessProposalEntity.setId(activityNegotiation.getId());
        businessProposalEntity.setProposal(new BusinessProposalEntity(activityNegotiation.getNegotiation().getId()));
        businessProposalEntity.setActivity(activityEntity);
        activityEntity.setRepresent(CalendarActivityType.NEGOTIATION);
        CalendarActivityBusinessProposalEntity negotiationEntitySaved = repository.save(businessProposalEntity);

        return Optional.of(getConverter().convert(negotiationEntitySaved));
    }

    @Override
    public BaseRepositoryLegacy<CalendarActivityBusinessProposalEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<CalendarActivityBusinessProposalEntity, ActivityNegotiation> getConverter() {
        return ((activityEntity, args) -> {

            ActivityNegotiation activityNegotiation = new ActivityNegotiation();
            activityNegotiation.setId(activityEntity.getId());
            activityNegotiation.setNegotiation(new Negotiation(activityEntity.getProposal().getId()));

            return activityNegotiation;
        });
    }
}
