package br.com.kproj.salesman.auditing.application.events;

import br.com.kproj.salesman.auditing.application.BusinessProposalAuditingApplication;
import br.com.kproj.salesman.auditing.application.subscribers.AuditingSubscriber;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuditingSubscriberTest {

    @InjectMocks
    private AuditingSubscriber subscriber;

    @Mock
    private BusinessProposalAuditingApplication application;

    @Mock
    private SecurityHelper security;

    @Test
    public void shouldRegisterAuditing() {
        BusinessProposal businessProposalMock = Mockito.mock(BusinessProposal.class);
        User userMock = Mockito.mock(User.class);
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setUser(userMock);

        given(security.getPrincipal()).willReturn(loggedUser);

        subscriber.persistAuditWhenDistinctLastVersion(businessProposalMock);

        verify(application, times(1)).registerAuditing(businessProposalMock, userMock);
    }

}