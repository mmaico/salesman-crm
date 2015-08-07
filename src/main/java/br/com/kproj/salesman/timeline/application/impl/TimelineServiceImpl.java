package br.com.kproj.salesman.timeline.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.TimelineRepository;
import br.com.kproj.salesman.infrastructure.service.impl.BaseModelServiceImpl;
import br.com.kproj.salesman.timeline.application.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimelineServiceImpl extends BaseModelServiceImpl<Timeline> implements TimelineService {

	
    @Autowired
    private TimelineRepository repository;

    public Timeline register(Timeline timeline, Person person) {

        if (!repository.findOne(person).isPresent()) {

        }
        return null;
    }

    public Timeline register(Timeline timeline, BusinessProposal proposal) {
        return null;
    }

    public Timeline register(Timeline timeline, Contact contact) {
        return null;
    }

    @Override
    public BaseRepository<Timeline, Long> getRepository() {
        return repository;
    }
}
