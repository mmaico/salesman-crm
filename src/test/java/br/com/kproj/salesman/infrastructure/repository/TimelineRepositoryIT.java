package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

public class TimelineRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private TimelineRepository repository;

    @Test
    public void shouldFindTimelineByPerson() {
        Long personId = 1l;

        Boolean exists = repository.exists(new Person(personId));

        assertThat(exists, Matchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldFindTimelineByContact() {
        Long contactId = 2l;

        Boolean exists = repository.exists(new Contact(contactId));

        assertThat(exists, Matchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldFindTimelineByBusinessProposal() {
        Long proposalId = 1l;

        Boolean exists = repository.exists(new BusinessProposal(proposalId));

        assertThat(exists, Matchers.is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenNotFound() {
        Long contactId = 1l;

        Boolean exists = repository.exists(new Contact(contactId));

        assertThat(exists, Matchers.is(Boolean.FALSE));
    }
}