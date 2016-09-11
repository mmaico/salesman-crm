package br.com.kproj.salesman.delivery.application.tasks;

import br.com.kproj.salesman.infrastructure.entity.builders.ChecklistBuilder;
import br.com.kproj.salesman.infrastructure.entity.task.ChecklistEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
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
public class ChecklistEntityApplicationImplTest {

    @InjectMocks
    private ChecklistApplicationImpl application;

    @Mock
    private ChecklistRepository repository;

    @Test
    public void shouldRegisterChecklist() {
        ChecklistEntity checklistEntityMock = mock(ChecklistEntity.class);
        ChecklistEntity checklistEntityMockSaved = mock(ChecklistEntity.class);

        given(repository.save(checklistEntityMock)).willReturn(checklistEntityMockSaved);
        given(checklistEntityMock.isNew()).willReturn(Boolean.TRUE);

        ChecklistEntity result = application.register(checklistEntityMock);

        MatcherAssert.assertThat(result, sameInstance(checklistEntityMockSaved));
    }

    @Test
    public void shouldReturnEmptyWhenTaskWithoutId() {
        TaskEntity taskEntity = new TaskEntity();

        List<ChecklistEntity> result = application.findCheckListBy(taskEntity);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnEmptyWhenTaskIsNull() {
        TaskEntity taskEntity = null;

        List<ChecklistEntity> result = application.findCheckListBy(taskEntity);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.TRUE));
    }

    @Test
    public void shouldFindChecklistByTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1l);

        given(repository.findCheckListBy(taskEntity)).willReturn(newArrayList(mock(ChecklistEntity.class)));

        List<ChecklistEntity> result = application.findCheckListBy(taskEntity);

        MatcherAssert.assertThat(result.isEmpty(), is(Boolean.FALSE));
    }

    @Test
    public void shouldDoNothingWhenChecklistWithoutId() {
        ChecklistEntity checklistEntity = new ChecklistEntity();

        application.completed(checklistEntity);

        Mockito.verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldMarkChecklistToDone() {
        ChecklistEntity checklistEntity = ChecklistBuilder.createChecklist(1l).build();
        ChecklistEntity checklistEntityDB = mock(ChecklistEntity.class);

        given(repository.getOne(1l)).willReturn(Optional.of(checklistEntityDB));

        application.completed(checklistEntity);

        verify(checklistEntityDB).setIsDone(Boolean.TRUE);
    }
}