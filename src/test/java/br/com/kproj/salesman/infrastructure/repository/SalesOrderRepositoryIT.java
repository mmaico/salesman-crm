package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.builders.ClientBuilder;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SalesOrderRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private SalesOrderRepository repository;

    @Test
    public void shouldReturnSalesOrderByClient() {

        Client client = ClientBuilder.createClient(2l).build();

        List<SalesOrder> result = repository.getOrdersByClient(client);

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getId(), is(1l));
    }
}