package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.core.Is.is;


public class NotificationRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private NotificationRepository repository;

    @Test
    public void shouldCountTaskNotification() {
        User user = UserBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountTaskNotificationBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }

    @Test
    public void shouldCountProposalNotification() {
        User user = UserBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountProposalBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }
}