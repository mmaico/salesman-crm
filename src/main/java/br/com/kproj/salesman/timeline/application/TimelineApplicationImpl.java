package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.IncidentEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.repository.*;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;
@Deprecated
@Service
public class TimelineApplicationImpl extends BaseModelServiceLegacyImpl<Timeline> implements TimelineApplication {

	
    @Autowired
    private TimelineRepository timelineRepository;


	private Map<Class<? extends TimelinePresent>, BaseRepositoryLegacy<? extends Identifiable, Long>> repositories = new HashMap<>();

	@Autowired
	public TimelineApplicationImpl(TimelineRepository timelineRepository, PersonRepository personRepository, BusinessProposalRepository proposalRepository,
								    TaskRepository taskRepository,
								    IncidentRepository incidentRepository
								   ) {
		this.timelineRepository = timelineRepository;

		//repositories.put(Individual.class, personRepository);
		//repositories.put(Person.class, personRepository);
		repositories.put(BusinessProposalEntity.class, proposalRepository);
		//repositories.put(ContactEntity.class, contactRepository);
		repositories.put(TaskEntity.class, taskRepository);
		repositories.put(IncidentEntity.class, incidentRepository);
	}

	@Override
	public <T extends TimelinePresent> Timeline register(T entity) {
		Timeline timeline = null;

		BaseRepositoryLegacy<? extends Identifiable, Long> repository = repositories.get(entity.getClass());
		TimelinePresent result = (TimelinePresent)repository.findOne(entity.getId());

		if (result.getTimeline() == null) {
			timeline = createTimeline(entity).build();
			timeline = timelineRepository.save(timeline);
			result.setTimeline(timeline);
		} else {
			timeline = result.getTimeline();
		}

		return timeline;
	}

	public BaseRepositoryLegacy<Timeline, Long> getRepository() {
        return timelineRepository;
    }


}
