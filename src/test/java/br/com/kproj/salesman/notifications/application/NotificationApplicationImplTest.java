package br.com.kproj.salesman.notifications.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.ApproverBuilder;
import br.com.kproj.salesman.infrastructure.entity.notification.ApprovalBusinessProposalNotification;
import br.com.kproj.salesman.infrastructure.entity.notification.Notification;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.repository.NotificationRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class NotificationApplicationImplTest {

    @InjectMocks
    private NotificationApplicationImpl application;

    @Mock
    private NotificationRepository repository;

    @Captor
    private ArgumentCaptor<List<Notification>> notificationsCaptor;

    @Test
    public void shoulSendNotificationToProposalApprovers() {
        RequestApproval requestApproval = getRequestApprovalStub();

        application.sendNotificationToProposalApprovers(requestApproval);

        verify(repository).save(notificationsCaptor.capture());

        List<Notification> result = notificationsCaptor.getValue();
        ApprovalBusinessProposalNotification notificationOne = (ApprovalBusinessProposalNotification) result.get(0);
        ApprovalBusinessProposalNotification notificationTwo = (ApprovalBusinessProposalNotification) result.get(1);

        assertThat(result.size(), Matchers.is(2));
        assertThat(notificationOne.getCreateDate(), Matchers.notNullValue());
        assertThat(notificationOne.getNotified(), Matchers.notNullValue());
        assertThat(notificationOne.getProposal(), sameInstance(requestApproval.getProposal()));

        assertThat(notificationTwo.getCreateDate(), Matchers.notNullValue());
        assertThat(notificationTwo.getNotified(), Matchers.notNullValue());
        assertThat(notificationTwo.getProposal(), sameInstance(requestApproval.getProposal()));
    }

    @Test
    public void shouldDoNothingWhenNotHaveApprovers() {
        RequestApproval requestApproval = getRequestApprovalStub();
        requestApproval.getApprovers().clear();

        application.sendNotificationToProposalApprovers(requestApproval);

        verifyNoMoreInteractions(repository);
    }


    private RequestApproval getRequestApprovalStub() {
        RequestApproval requestApproval = new RequestApproval();
        requestApproval.setProposal(mock(BusinessProposal.class));
        requestApproval.addApprover(ApproverBuilder.createApprover().withApprover(mock(User.class)).build());
        requestApproval.addApprover(ApproverBuilder.createApprover().withApprover(mock(User.class)).build());

        return requestApproval;
    }
}