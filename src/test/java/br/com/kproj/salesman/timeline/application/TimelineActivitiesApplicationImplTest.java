package br.com.kproj.salesman.timeline.application;

import br.com.kproj.salesman.infrastructure.entity.AppFile;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TimelineActivity;
import br.com.kproj.salesman.infrastructure.repository.TimelineActivitiesRepository;
import br.com.kproj.salesman.infrastructure.service.FilePersistHelper;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TimelineActivitiesApplicationImplTest {

    @InjectMocks
    private TimelineActivitiesApplicationImpl timelineActivitiesApplication;

    @Mock
    private TimelineApplication service;

    @Mock
    private TimelineActivitiesRepository repository;

    @Mock
    private FilePersistHelper filePersistHelper;

    @Mock
    private EventBus eventBus;

    @Test
    public void shouldRegisterTimeActivyPerson() {
        Person person = mock(Person.class);
        Timeline timelineMock = mock(Timeline.class);
        TimelineActivity activity = mock(LogActivity.class);

        TimelineActivity activitySaved = mock(TimelineActivity.class);
        ArrayList<AppFile> files = newArrayList(mock(AppFile.class), mock(AppFile.class));

        given(timelineMock.isNew()).willReturn(Boolean.TRUE);
        given(activity.isNew()).willReturn(Boolean.TRUE);
        given(activity.getFiles()).willReturn(files);
        given(service.register(person)).willReturn(timelineMock);
        given(repository.save(activity)).willReturn(activitySaved);

        Timeline timelineResult = timelineActivitiesApplication.register(person, activity);

        assertThat(timelineResult, sameInstance(timelineMock));
        verify(this.filePersistHelper).saveFile(activitySaved, files);
        verify(timelineMock).addActivity(activitySaved);
    }


    @Test
    public void shouldRegisterTimeActivyBusinessProposal() {
        BusinessProposal proposal = mock(BusinessProposal.class);
        Timeline timelineMock = mock(Timeline.class);
        TimelineActivity activity = mock(LogActivity.class);

        TimelineActivity activitySaved = mock(TimelineActivity.class);
        ArrayList<AppFile> files = newArrayList(mock(AppFile.class), mock(AppFile.class));

        given(timelineMock.isNew()).willReturn(Boolean.TRUE);
        given(activity.isNew()).willReturn(Boolean.TRUE);
        given(activity.getFiles()).willReturn(files);
        given(service.register(proposal)).willReturn(timelineMock);
        given(repository.save(activity)).willReturn(activitySaved);

        Timeline timelineResult = timelineActivitiesApplication.register(proposal, activity);

        assertThat(timelineResult, sameInstance(timelineMock));
        verify(this.filePersistHelper).saveFile(activitySaved, files);
        verify(timelineMock).addActivity(activitySaved);
    }

    @Test
    public void shouldRegisterTimeActivyContact() {
        ContactEntity contact = mock(ContactEntity.class);
        Timeline timelineMock = mock(Timeline.class);
        TimelineActivity activity = mock(LogActivity.class);

        TimelineActivity activitySaved = mock(TimelineActivity.class);
        ArrayList<AppFile> files = newArrayList(mock(AppFile.class), mock(AppFile.class));

        given(timelineMock.isNew()).willReturn(Boolean.TRUE);
        given(activity.isNew()).willReturn(Boolean.TRUE);
        given(activity.getFiles()).willReturn(files);
        given(service.register(contact)).willReturn(timelineMock);
        given(repository.save(activity)).willReturn(activitySaved);

        Timeline timelineResult = timelineActivitiesApplication.register(contact, activity);

        assertThat(timelineResult, sameInstance(timelineMock));
        verify(this.filePersistHelper).saveFile(activitySaved, files);
        verify(timelineMock).addActivity(activitySaved);
    }

    @Test
    public void shouldRegisterTimeActivyTask() {
        Task task = mock(Task.class);
        Timeline timelineMock = mock(Timeline.class);
        TimelineActivity activity = mock(LogActivity.class);

        TimelineActivity activitySaved = mock(TimelineActivity.class);
        ArrayList<AppFile> files = newArrayList(mock(AppFile.class), mock(AppFile.class));

        given(timelineMock.isNew()).willReturn(Boolean.TRUE);
        given(activity.isNew()).willReturn(Boolean.TRUE);
        given(activity.getFiles()).willReturn(files);
        given(service.register(task)).willReturn(timelineMock);
        given(repository.save(activity)).willReturn(activitySaved);

        Timeline timelineResult = timelineActivitiesApplication.register(task, activity);

        assertThat(timelineResult, sameInstance(timelineMock));
        verify(this.filePersistHelper).saveFile(activitySaved, files);
        verify(timelineMock).addActivity(activitySaved);
    }

    @Test
    public void shouldGetActivityFile() {
        TimelineActivity timelineActivity = mock(TimelineActivity.class);
        AppFile appFile = mock(AppFile.class);

        given(timelineActivity.isNew()).willReturn(Boolean.FALSE);

        this.timelineActivitiesApplication.getActivityFile(timelineActivity, appFile);

        verify(filePersistHelper).getFile(timelineActivity, appFile);
    }

    @Test
    public void shouldReturnEmptyfileWhenTimelineActivityNoHaveId() {
        TimelineActivity timelineActivity = mock(TimelineActivity.class);
        AppFile appFile = mock(AppFile.class);

        given(timelineActivity.isNew()).willReturn(Boolean.TRUE);

        byte[] activityFile = this.timelineActivitiesApplication.getActivityFile(timelineActivity, appFile);

        assertThat(activityFile.length, is(0));
    }






}