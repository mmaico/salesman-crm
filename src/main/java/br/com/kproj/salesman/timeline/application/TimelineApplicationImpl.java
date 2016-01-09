package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.*;
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

   
    @Override
    public Timeline register(Person person) {
    	Timeline timeline = null;

		Person personLoaded = personRepository.findOne(person.getId());

		if (personLoaded.getTimeline() == null) {
			timeline = createTimeline(personLoaded).build();
			timeline = repository.save(timeline);
			personLoaded.setTimeline(timeline);
		} else {
			timeline = personLoaded.getTimeline();
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
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
