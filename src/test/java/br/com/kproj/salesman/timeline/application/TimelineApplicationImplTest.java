package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.infrastructure.repository.PersonRepository;
import br.com.kproj.salesman.infrastructure.repository.TimelineRepository;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TimelineApplicationImplTest {

	@InjectMocks
	private TimelineApplicationImpl service;

	@Mock
	private TimelineRepository repository;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private BusinessProposalRepository proposalRepository;

	@Mock
	private ContactRepository contactRepository;

	@Mock
	private TaskRepository taskRepository;
	
	
	
	@Test
	public void shouldReturnTimelineWhenAlreadyExists() {
		Timeline timelineMock = Mockito.mock(Timeline.class);
		Person person = new Person();
		person.setId(1l);

		Individual personDB = spy(new Individual());
		personDB.setId(1l);
		personDB.setTimeline(timelineMock);

		given(this.personRepository.findOne(1l)).willReturn(personDB);
		
		Timeline result = service.register(person);

		verify(this.repository, times(0)).save(Mockito.any(Timeline.class));
		assertThat(result, sameInstance(timelineMock));
	}
	
	@Test
	public void shouldCreateAnTimelineWhenNotExist() {
		Timeline timelineMock = mock(Timeline.class);
		Individual person = new Individual();
		person.setId(1l);

		Individual personDB = spy(new Individual());
		personDB.setId(1l);
		personDB.setTimeline(null);
		
		given(this.personRepository.findOne(1l)).willReturn(personDB);
		given(this.repository.save(Mockito.any(Timeline.class))).willReturn(timelineMock);
		
		Timeline result = service.register(person);

		verify(personDB).setTimeline(timelineMock);
		assertThat(result, sameInstance(timelineMock));
	}

}
