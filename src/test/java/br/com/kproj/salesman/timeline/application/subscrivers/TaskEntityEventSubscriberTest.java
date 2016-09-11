package br.com.kproj.salesman.timeline.application.subscrivers;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.TaskActivity;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeStatusMessage;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskEntityEventSubscriberTest {

    @InjectMocks
    private TaskEventSubscriber subscriber;

    @Mock
    private TimelineActivitiesApplication application;

    @Captor
    private ArgumentCaptor<TaskActivity> taskActivityCaptor;

    @Test
    public void shouldPostInTimelineChangeStatusFromTask() {
        TaskEntity taskmock = mock(TaskEntity.class);
        UserEntity userMock = mock(UserEntity.class);
        TaskStatus oldStatus = TaskStatus.STATED;

        TaskChangeStatusMessage message = TaskChangeStatusMessage.create(taskmock, userMock, oldStatus);

        given(taskmock.getStatus()).willReturn(TaskStatus.DONE);
        subscriber.createActivityTimelineOnChangeTaskStatus(message);

        verify(application).register(eq(taskmock), Mockito.any(TaskActivity.class));

    }

    @Test
    public void shouldbuildCorreclyTaskActivity() {
        String descriptionExpected = TaskEventSubscriber.MESSAGE
                .replace("{OLD}", TaskStatus.STATED.get()).replace("{NEW}", TaskStatus.DONE.get());
        TaskEntity taskmock = mock(TaskEntity.class);
        UserEntity userMock = mock(UserEntity.class);
        TaskStatus oldStatus = TaskStatus.STATED;

        TaskChangeStatusMessage message = TaskChangeStatusMessage.create(taskmock, userMock, oldStatus);

        given(userMock.getId()).willReturn(10l);
        given(taskmock.getStatus()).willReturn(TaskStatus.DONE);
        subscriber.createActivityTimelineOnChangeTaskStatus(message);

        verify(application).register(eq(taskmock), taskActivityCaptor.capture());
        TaskActivity result = taskActivityCaptor.getValue();

        MatcherAssert.assertThat(result.getDescription(), is(descriptionExpected));
        MatcherAssert.assertThat(result.getUser().getId(), is(10l));
    }


}