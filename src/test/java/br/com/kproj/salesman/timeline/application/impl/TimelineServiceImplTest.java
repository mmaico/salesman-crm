package br.com.kproj.salesman.timeline.application.impl;

import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.kproj.salesman.infrastructure.entity.person.Individual;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.repository.TimelineRepository;

@RunWith(MockitoJUnitRunner.class)
public class TimelineServiceImplTest {

	@InjectMocks
	private TimelineServiceImpl service;
	
	@Mock
	private TimelineRepository repository;
	
	
	
	@Test
	public void shouldSaveAndReturnATimelineByPerson() {
		Person person = new Individual();
		person.setId(1l);
		Timeline timeline = new Timeline();
	
		given(this.repository.findOne(person)).willReturn(of(timeline));
		
		Timeline result = service.register(person);
		
		assertThat(result, sameInstance(timeline));
	}
	
	@Test
	public void shouldCreateAnTimelineWhenNotExist() {
		Timeline timelineMock = Mockito.mock(Timeline.class);
		Individual person = new Individual();
		person.setId(1l);
		
		given(this.repository.findOne(person)).willReturn(Optional.empty());
		given(this.repository.save(Mockito.any(Timeline.class))).willReturn(timelineMock);
		
		Timeline result = service.register(person);
		
		assertThat(result, sameInstance(timelineMock));
	}

}
