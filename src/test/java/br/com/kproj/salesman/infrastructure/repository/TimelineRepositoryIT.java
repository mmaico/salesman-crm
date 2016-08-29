package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TimelineRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private TimelineRepository repository;

    @Test
    public void shouldFindTimelineByPerson() {
        Long personId = 1l;

        Optional<Timeline> result = repository.findOne(new Person(personId));

        assertThat(result.get().getId(), is(1l));
    }

    @Test
    public void shouldFindTimelineByContact() {
        Long contactId = 2l;

        Optional<Timeline> result = repository.findOne(new ContactEntity(contactId));

        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getId(), is(4l));
    }

    @Test
    public void shouldFindTimelineByBusinessProposal() {
        Long proposalId = 1l;

        Optional<Timeline> result = repository.findOne(new BusinessProposalEntity(proposalId));

        assertThat(result.get().getId(), is(3l));
    }

    @Test
    public void shouldReturnNullWhenNotFound() {
        Long contactId = 1l;

        Optional<Timeline> result = repository.findOne(new ContactEntity(contactId));

        assertThat(result.isPresent(), is(Boolean.FALSE));
    }
}