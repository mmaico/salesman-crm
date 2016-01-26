package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.*;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;

@Service
public class TimelineApplicationImpl extends BaseModelServiceImpl<Timeline> implements TimelineApplication {

	
    @Autowired
    private TimelineRepository repository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BusinessProposalRepository proposalRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private TaskRepository taskRepository;

   
    @Override
    public Timeline register(Person person) {
    	Timeline timeline = null;

		Optional<Person> personLoaded = personRepository.getOne(person.getId());

		if (personLoaded.isPresent() && personLoaded.get().getTimeline() == null) {
			timeline = createTimeline(personLoaded.get()).build();
			timeline = repository.save(timeline);
			personLoaded.get().setTimeline(timeline);
		} else {
			timeline = personLoaded.get().getTimeline();
		}

        return timeline;
    }


    @Override
    public Timeline register(BusinessProposal proposal) {
    	Timeline timeline = null;

		BusinessProposal proposalLoaded = proposalRepository.findOne(proposal.getId());

		if (proposalLoaded.getTimeline() == null) {
			timeline = createTimeline(proposalLoaded).build();
			timeline = repository.save(timeline);
			proposalLoaded.setTimeline(timeline);
		} else {
			timeline = proposalLoaded.getTimeline();
		}

        return timeline;
    }

    @Override
    public Timeline register(Contact contact) {
		Timeline timeline = null;

		Contact contactLoaded = contactRepository.findOne(contact.getId());

		if (contactLoaded.getTimeline() == null) {
			timeline = createTimeline(contactLoaded).build();
			timeline = repository.save(timeline);
			contactLoaded.setTimeline(timeline);
		} else {
			timeline = contactLoaded.getTimeline();
		}

		return timeline;
    }

	@Override
	public Timeline register(Task task) {
		Timeline timeline = null;

		Task taskLoaded = taskRepository.findOne(task.getId());

		if (taskLoaded.getTimeline() == null) {
			timeline = createTimeline(taskLoaded).build();
			timeline = repository.save(timeline);
			taskLoaded.setTimeline(timeline);
		} else {
			timeline = taskLoaded.getTimeline();
		}

		return timeline;
	}

	@Override
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
