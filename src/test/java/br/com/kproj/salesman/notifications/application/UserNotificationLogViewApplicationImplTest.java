package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.notification.UserNotificationLogView;
import br.com.kproj.salesman.infrastructure.repository.UserNotificationLogViewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class UserNotificationLogViewApplicationImplTest {

    @InjectMocks
    private UserNotificationLogViewApplicationImpl application;

    @Mock
    private UserNotificationLogViewRepository repository;

    @Test
    public void shouldRegisterUserNotificationLogView() {
        UserNotificationLogView logView = new UserNotificationLogView();
        logView.setUser(UserBuilder.createUser(1l).build());
        UserNotificationLogView logViewDBMock = mock(UserNotificationLogView.class);

        given(repository.save(logView)).willReturn(logViewDBMock);

        Optional<UserNotificationLogView> result = application.register(logView);


        assertThat(result.get(), sameInstance(logViewDBMock));
        assertThat(logView.getLastVisualization(), notNullValue());
    }

    @Test
    public void shouldReturnEmptyWhenUserWithoutId() {
        UserNotificationLogView logView = new UserNotificationLogView();
        logView.setUser(UserBuilder.createUser().build());

        Optional<UserNotificationLogView> result = application.register(logView);

        verifyNoMoreInteractions(repository);
        assertThat(result.isPresent(), is(Boolean.FALSE));
    }


}