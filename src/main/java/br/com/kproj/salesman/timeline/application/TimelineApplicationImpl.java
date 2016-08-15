package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.assistants.activities.infrastructure.PersonalAcvitityRepository;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.Incident;
import br.com.kproj.salesman.infrastructure.entity.activities.PersonalActivity;
import br.com.kproj.salesman.infrastructure.entity.leads.Lead;
import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.repository.*;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceLegacyImpl;
import br.com.kproj.salesman.negotiation.infrastructure.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;

@Service
public class TimelineApplicationImpl extends BaseModelServiceLegacyImpl<Timeline> implements TimelineApplication {

	
    @Autowired
    private TimelineRepository timelineRepository;


	private Map<Class<? extends TimelinePresent>, BaseRepositoryLegacy<? extends Identifiable, Long>> repositories = new HashMap<>();

	@Autowired
	public TimelineApplicationImpl(TimelineRepository timelineRepository, PersonRepository personRepository, BusinessProposalRepository proposalRepository,
								   ContactRepository contactRepository, TaskRepository taskRepository,
								   PersonalAcvitityRepository personalAcvitityRepository, IncidentRepository incidentRepository,
								   LeadRepository leadRepository) {
		this.timelineRepository = timelineRepository;

		repositories.put(Individual.class, personRepository);
		repositories.put(Person.class, personRepository);
		repositories.put(BusinessProposal.class, proposalRepository);
		repositories.put(Contact.class, contactRepository);
		repositories.put(Task.class, taskRepository);
		repositories.put(PersonalActivity.class, personalAcvitityRepository);
		repositories.put(Incident.class, incidentRepository);
		repositories.put(Lead.class, leadRepository);
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
