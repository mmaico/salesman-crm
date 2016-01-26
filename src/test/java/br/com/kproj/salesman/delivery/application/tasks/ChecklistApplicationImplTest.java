package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.Checklist;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.task.ChecklistRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChecklistApplicationImplTest {

    @InjectMocks
    private ChecklistApplicationImpl application;

    @Mock
    private ChecklistRepository repository;

    @Test
    public void shouldRegisterChecklist() {
        Checklist checklistMock = mock(Checklist.class);
        Checklist checklistMockSaved = mock(Checklist.class);

        given(repository.save(checklistMock)).willReturn(checklistMockSaved);
        given(checklistMock.isNew()).willReturn(Boolean.TRUE);

        Checklist result = application.register(checklistMock);

        MatcherAssert.assertThat(result, sameInstance(checklistMockSaved));
    }

    @Test
    public void shouldReturnEmptyWhenTaskWithoutId() {
        Task task = new Task();

        List<Checklist> result = application.findCheckListBy(task);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnEmptyWhenTaskIsNull() {
        Task task = null;

        List<Checklist> result = application.findCheckListBy(task);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldFindChecklistByTask() {
        Task task = new Task();
        task.setId(1l);

        given(repository.findCheckListBy(task)).willReturn(newArrayList(mock(Checklist.class)));

        List<Checklist> result = application.findCheckListBy(task);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.FALSE));
    }

    @Test
    public void shouldDoNothingWhenChecklistWithoutId() {
        Checklist checklist = new Checklist();

        application.completed(checklist);

        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldMarkChecklistToDone() {
        Checklist checklist = ChecklistBuilder.createChecklist(1l).build();
        Checklist checklistDB = mock(Checklist.class);

        given(repository.getOne(1l)).willReturn(Optional.of(checklistDB));

        application.completed(checklist);

        verify(checklistDB).setIsDone(Boolean.TRUE);
    }
}