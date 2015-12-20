package br.com.kproj.salesman.infrastructure.security;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class UserSecurityInfoServiceTest {

    @InjectMocks
    private UserSecurityInfoService service;

    @Mock
    private UserRepository repository;



    @Test
    public void shouldFindUserByLoginAndPassword() {
        String login = "login";
        String password = "password";
        Optional<User> userMock = Optional.of(Mockito.mock(User.class));

        given(repository.findByLoginAndPassword(login, password)).willReturn(userMock);

        Optional<User> result = service.getUser(login, password);

        assertThat(result.get(), Matchers.sameInstance(userMock));
    }

    @Test
    public void shouldReturnEmptyWhenUserNotFound() {
        String login = "login";
        String password = "password";

        given(repository.findByLoginAndPassword(login, password)).willReturn(null);

        Optional<User> result = service.getUser(login, password);

        assertThat(result.isPresent(), is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnEmptyWhenLoginIsNull() {
        String login = null;
        String password = "password";

        Optional<User> result = service.getUser(login, password);

        verifyNoMoreInteractions(this.repository);
        assertThat(result.isPresent(), is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnEmptyWhenPasswordIsNull() {
        String login = "login";
        String password = "";

        Optional<User> result = service.getUser(login, password);

        verifyNoMoreInteractions(this.repository);
        assertThat(result.isPresent(), is(Boolean.FALSE));
    }


}