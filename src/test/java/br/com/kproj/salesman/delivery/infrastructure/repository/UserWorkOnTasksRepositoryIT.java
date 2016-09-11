package br.com.kproj.salesman.delivery.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class UserWorkOnTasksRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private UserWorkOnTasksRepository repository;

    @Test
    public void shouldReturnAllUsersWorksInTasks() {

        List<UserEntity> result = repository.findUsersWorksInTasks();

        assertThat(result.size(), is(2));
        assertThat(result.contains(createUser(1l).build()), is(Boolean.TRUE));
        assertThat(result.contains(createUser(2l).build()), is(Boolean.TRUE));
    }

    @Test
    public void shouldCountActingByOnTasksByUser() {
        UserEntity user = createUser(1l).build();

        Long result = repository.countOnAllTasksBy(user, TaskStatus.WAITING);

        assertThat(result, is(2l));
    }

    @Test
    public void shouldReturnUsersWorkInTask() {
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder(1l).build();

        List<UserEntity> result = repository.findUsersWorksOnSalesOrder(salesOrderEntity);

        assertThat(result.size(), is(2));
        assertThat(result.contains(createUser(1l).build()), is(Boolean.TRUE));
        assertThat(result.contains(createUser(2l).build()), is(Boolean.TRUE));
    }

    @Test
    public void shouldCountActingOnTaskByUserAndStatus() {
        UserEntity user = createUser(1l).build();
        TaskStatus status = TaskStatus.PROBLEM;
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder(1l).build();

        Long result = repository.countOnTaskBy(user, salesOrderEntity, status);

        assertThat(result, is(2l));
    }
}