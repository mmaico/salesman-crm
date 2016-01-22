package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder.createUser;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ActDeliverySalesRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ActDeliverySalesRepository repository;

    @Test
    public void shouldReturnOnlyItemsInActDelivery() {
        List<SalesOrder> result = repository.findSalesOrderOutActDelivery();

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(3l));
        assertThat(result.get(1).getId(), is(4l));
    }

    @Test
    public void shouldReturnOnlyInActWithDistinctAndOrderingByDeliveryForecast() {
        List<SalesOrder> result = repository.findSalesOrderInActDelivery();

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(2l));
        assertThat(result.get(1).getId(), is(1l));
    }

    @Test
    public void shouldReturnAllUsersWithActDeliverySigned() {
        List<User> users = repository.findUsersWithSignedDelivery();

        assertThat(users.size(), is(2));
        assertThat(users, hasItem(createUser(1l).build()));
        assertThat(users, hasItem(createUser(2l).build()));
    }

}