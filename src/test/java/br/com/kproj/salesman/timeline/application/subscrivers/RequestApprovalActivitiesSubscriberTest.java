package br.com.kproj.salesman.timeline.application.subscrivers;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.events.messages.RequestApprovalFinalizeMessage;
import br.com.kproj.salesman.infrastructure.events.messages.RequestNewApprovalMessage;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class RequestApprovalActivitiesSubscriberTest {

    @InjectMocks
    private RequestApprovalActivitiesSubscriber subscriber;

    @Mock
    private TimelineActivitiesApplication application;

    @Captor
    private ArgumentCaptor<LogActivity> logActivityCaptor;

    @Captor
    private ArgumentCaptor<BusinessProposal> proposalCaptor;

    @Test
    public void shouldGenerateActivyByRequestNewAapproval() {
        UserEntity requestor = mock(UserEntity.class);
        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setUserRequester(requestor);
        RequestNewApprovalMessage message = RequestNewApprovalMessage.create(requestApproval);

        subscriber.generateActivityByRequestNewApproval(message);

        verify(application).register(Mockito.any(BusinessProposal.class), logActivityCaptor.capture());

        LogActivity logActivity = logActivityCaptor.getValue();

        assertThat(logActivity.getDescription(), is(RequestApprovalActivitiesSubscriber.MESSAGE));
        assertThat(logActivity.getType(), is(LogActivityTypeEnum.START_APPROVAL));
        assertThat(logActivity.getUser(), sameInstance(requestor));

    }

    @Test
    public void shouldVerifyIfCorreclyProposalSendToApplication() {
        BusinessProposal businessProposalMock = mock(BusinessProposal.class);
        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setUserRequester(mock(UserEntity.class));
        requestApproval.setProposal(businessProposalMock);
        RequestNewApprovalMessage message = RequestNewApprovalMessage.create(requestApproval);

        subscriber.generateActivityByRequestNewApproval(message);

        verify(application).register(proposalCaptor.capture(), Mockito.any(LogActivity.class));

        BusinessProposal businessProposal = proposalCaptor.getValue();

        assertThat(businessProposal, sameInstance(businessProposalMock));
    }

    @Test
    public void shouldGenerateActivityByFinalizeRequestApproval() {
        UserEntity requestor = mock(UserEntity.class);
        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setUserRequester(requestor);
        RequestApprovalFinalizeMessage message = RequestApprovalFinalizeMessage.create(requestApproval);

        subscriber.generateActivityByFinalizeRequestApproval(message);

        verify(application).register(Mockito.any(BusinessProposal.class), logActivityCaptor.capture());

        LogActivity logActivity = logActivityCaptor.getValue();

        assertThat(logActivity.getDescription(), is(RequestApprovalActivitiesSubscriber.MESSAGE_FINALIZE));
        assertThat(logActivity.getType(), is(LogActivityTypeEnum.FINALIZE_APPROVAL));
        assertThat(logActivity.getUser(), sameInstance(requestor));
    }

    @Test
    public void shouldVerifyIfCorreclyProposalSendToApplicationOnFinalizeRequestApproval() {
        BusinessProposal businessProposalMock = mock(BusinessProposal.class);
        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setUserRequester(mock(UserEntity.class));
        requestApproval.setProposal(businessProposalMock);
        RequestApprovalFinalizeMessage message = RequestApprovalFinalizeMessage.create(requestApproval);

        subscriber.generateActivityByFinalizeRequestApproval(message);

        verify(application).register(proposalCaptor.capture(), Mockito.any(LogActivity.class));

        BusinessProposal businessProposal = proposalCaptor.getValue();

        assertThat(businessProposal, sameInstance(businessProposalMock));
    }
}