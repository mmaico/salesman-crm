package br.com.kproj.salesman.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

}