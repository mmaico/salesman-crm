package br.com.kproj.salesman.sales.application.subscribers;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.BusinessProposalClosedWonMessage;
import br.com.kproj.salesman.sales.application.SalesOrderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BusinessProposalEntitySubscriberTest {

    @InjectMocks
    private BusinessProposalSubscriber subscriber;

    @Mock
    private SalesOrderApplication application;

    @Test
    public void shouldGenerateSalesOrder() {
        BusinessProposalEntity proposal = mock(BusinessProposalEntity.class);

        BusinessProposalClosedWonMessage message = BusinessProposalClosedWonMessage.create(proposal);

        subscriber.generateSalesOrder(message);

        verify(application).register(proposal);
    }
}