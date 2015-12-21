package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.TimelineRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;

@Service
public class TimelineApplicationImpl extends BaseModelServiceImpl<Timeline> implements TimelineApplication {

	
    @Autowired
    private TimelineRepository repository;

   
    @Override
    public Timeline register(Person person) {
    	Timeline timeline = null;
    	
    	synchronized (this) {
			Optional<Timeline> result = repository.findOne(person);
			if (!result.isPresent()) {
				timeline = createTimeline(person).build();
				timeline = repository.save(timeline);
			} else {
				timeline = result.get();
			}
		}

        return timeline;
    }

    @Override
    public Timeline register(BusinessProposal proposal) {
    	Timeline timeline = null;
    	
    	synchronized (this) {
			Optional<Timeline> result = repository.findOne(proposal);
			if (!result.isPresent()) {
				timeline = createTimeline(proposal).build();
				timeline = repository.save(timeline);
			} else {
				timeline = result.get();
			}
		}

        return timeline;
    }

    @Override
    public Timeline register(Contact contact) {
    	Timeline timeline = null;
    	
    	synchronized (this) {
			Optional<Timeline> result = repository.findOne(contact);
			if (!result.isPresent()) {
				timeline = createTimeline(contact).build();
				timeline = repository.save(timeline);
			} else {
				timeline = result.get();
			}
		}

        return timeline;
    }
   
    @Override
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
