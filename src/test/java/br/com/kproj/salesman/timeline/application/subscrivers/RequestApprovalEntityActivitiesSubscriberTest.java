package br.com.kproj.salesman.timeline.application.subscrivers;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.LogActivityTypeEnum;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
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
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class RequestApprovalEntityActivitiesSubscriberTest {

    @InjectMocks
    private RequestApprovalActivitiesSubscriber subscriber;

    @Mock
    private TimelineActivitiesApplication application;

    @Captor
    private ArgumentCaptor<LogActivity> logActivityCaptor;

    @Captor
    private ArgumentCaptor<BusinessProposalEntity> proposalCaptor;

    @Test
    public void shouldGenerateActivyByRequestNewAapproval() {
        UserEntity requestor = mock(UserEntity.class);
        RequestApprovalEntity requestApprovalEntity = new RequestApprovalEntity();
        requestApprovalEntity.setUserRequester(requestor);
        RequestNewApprovalMessage message = RequestNewApprovalMessage.create(requestApprovalEntity);

        subscriber.generateActivityByRequestNewApproval(message);

        verify(application).register(Mockito.any(BusinessProposalEntity.class), logActivityCaptor.capture());

        LogActivity logActivity = logActivityCaptor.getValue();

        assertThat(logActivity.getDescription(), is(RequestApprovalActivitiesSubscriber.MESSAGE));
        assertThat(logActivity.getType(), is(LogActivityTypeEnum.START_APPROVAL));
        assertThat(logActivity.getUser(), sameInstance(requestor));

    }

    @Test
    public void shouldVerifyIfCorreclyProposalSendToApplication() {
        BusinessProposalEntity businessProposalEntityMock = mock(BusinessProposalEntity.class);
        RequestApprovalEntity requestApprovalEntity = new RequestApprovalEntity();
        requestApprovalEntity.setUserRequester(mock(UserEntity.class));
        requestApprovalEntity.setProposal(businessProposalEntityMock);
        RequestNewApprovalMessage message = RequestNewApprovalMessage.create(requestApprovalEntity);

        subscriber.generateActivityByRequestNewApproval(message);

        verify(application).register(proposalCaptor.capture(), Mockito.any(LogActivity.class));

        BusinessProposalEntity businessProposalEntity = proposalCaptor.getValue();

        assertThat(businessProposalEntity, sameInstance(businessProposalEntityMock));
    }

    @Test
    public void shouldGenerateActivityByFinalizeRequestApproval() {
        UserEntity requestor = mock(UserEntity.class);
        RequestApprovalEntity requestApprovalEntity = new RequestApprovalEntity();
        requestApprovalEntity.setUserRequester(requestor);
        RequestApprovalFinalizeMessage message = RequestApprovalFinalizeMessage.create(requestApprovalEntity);

        subscriber.generateActivityByFinalizeRequestApproval(message);

        verify(application).register(Mockito.any(BusinessProposalEntity.class), logActivityCaptor.capture());

        LogActivity logActivity = logActivityCaptor.getValue();

        assertThat(logActivity.getDescription(), is(RequestApprovalActivitiesSubscriber.MESSAGE_FINALIZE));
        assertThat(logActivity.getType(), is(LogActivityTypeEnum.FINALIZE_APPROVAL));
        assertThat(logActivity.getUser(), sameInstance(requestor));
    }

    @Test
    public void shouldVerifyIfCorreclyProposalSendToApplicationOnFinalizeRequestApproval() {
        BusinessProposalEntity businessProposalEntityMock = mock(BusinessProposalEntity.class);
        RequestApprovalEntity requestApprovalEntity = new RequestApprovalEntity();
        requestApprovalEntity.setUserRequester(mock(UserEntity.class));
        requestApprovalEntity.setProposal(businessProposalEntityMock);
        RequestApprovalFinalizeMessage message = RequestApprovalFinalizeMessage.create(requestApprovalEntity);

        subscriber.generateActivityByFinalizeRequestApproval(message);

        verify(application).register(proposalCaptor.capture(), Mockito.any(LogActivity.class));

        BusinessProposalEntity businessProposalEntity = proposalCaptor.getValue();

        assertThat(businessProposalEntity, sameInstance(businessProposalEntityMock));
    }
}