package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.TimelinePresent;
import br.com.kproj.salesman.infrastructure.repository.*;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;

@Service
public class TimelineApplicationImpl extends BaseModelServiceImpl<Timeline> implements TimelineApplication {

	
    @Autowired
    private TimelineRepository timelineRepository;


	private Map<Class<? extends TimelinePresent>, BaseRepository<? extends Identifiable, Long>> repositories = new HashMap<>();

	@Autowired
	public TimelineApplicationImpl(TimelineRepository timelineRepository, PersonRepository personRepository, BusinessProposalRepository proposalRepository,
								   ContactRepository contactRepository, TaskRepository taskRepository) {
		this.timelineRepository = timelineRepository;

		repositories.put(Individual.class, personRepository);
		repositories.put(Person.class, personRepository);
		repositories.put(BusinessProposal.class, proposalRepository);
		repositories.put(Contact.class, contactRepository);
		repositories.put(Task.class, taskRepository);

	}

	@Override
	public <T extends TimelinePresent> Timeline register(T entity) {
		Timeline timeline = null;

		BaseRepository<? extends Identifiable, Long> repository = repositories.get(entity.getClass());
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

	public BaseRepository<Timeline, Long> getRepository() {
        return timelineRepository;
    }


}
