package br.com.kproj.salesman.timeline.application.impl;

import static br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder.createTimeline;

import java.util.Optional;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.builders.TimelineBuilder;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineItem;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.TimelineRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesService;
import br.com.kproj.salesman.timeline.application.TimelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimelineActivitiesServiceImpl extends BaseModelServiceImpl<Timeline> implements TimelineActivitiesService {

	
    @Autowired
    private TimelineRepository repository;

    @Override
    public Timeline register(Person person, TimelineItem item) {
    	Timeline timeline = null;
    	
    	

        return null;
    }

    @Override
    public Timeline register(BusinessProposal proposal, TimelineItem item) {
        return null;
    }

    @Override
    public Timeline register(Contact contact, TimelineItem item) {
        return null;
    }

    @Override
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
