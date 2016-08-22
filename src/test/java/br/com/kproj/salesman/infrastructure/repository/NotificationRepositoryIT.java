package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
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
        UserEntity user = UserEntityBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountTaskNotificationBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }

    @Test
    public void shouldCountProposalNotification() {
        UserEntity user = UserEntityBuilder.createUser(1l).build();
        Date date = DateHelper.convertToDate("10/02/2016");

        Long count = repository.findCountProposalBy(user, date);

        MatcherAssert.assertThat(count, is(1l));
    }
}