package br.com.kproj.salesman.negotiation.proposal.approval.application.events;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.events.messages.TimelineSaveMessage;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUserBuilder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.negotiation.proposal.approval.application.RequestApprovalApplication;
import br.com.kproj.salesman.negotiation.proposal.approval.application.subscribers.TimelineAvaluationApproverSubscriber;
import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import static br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder.createUser;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class TimelineAvaluationApproverSubscriberTest {

    @InjectMocks
    private TimelineAvaluationApproverSubscriber subscriber;

    @Mock
    private RequestApprovalApplication application;

    @Mock
    private SecurityHelper security;


    @Test
    public void shouldRequestApproval() {
        LoggedUser loggedUser = LoggedUserBuilder
                .createLoggedUser("login", createUser(2l).build(), Sets.newHashSet()).build();
        BusinessProposalEntity proposalMock = Mockito.mock(BusinessProposalEntity.class);

        BusinessProposalApprovalActivity activity = new BusinessProposalApprovalActivity();
        activity.setId(1l);
        activity.setAvaluation(ApproverStatus.APPROVED);
        TimelineSaveMessage timelineEvent = TimelineSaveMessage.createTimelineEvent(mock(UserEntity.class), proposalMock, activity);

        given(security.getPrincipal()).willReturn(loggedUser);

        subscriber.requestApprovalAvaluation(timelineEvent);


        verify(application).evaluationApprover(proposalMock, loggedUser.getUser(), ApproverStatus.APPROVED);
    }

    @Test
    public void shouldDoNothingWhenNotBusinessProposalApprovalActivity() {
        BusinessProposalEntity proposalMock = Mockito.mock(BusinessProposalEntity.class);

        LogActivity activity = new LogActivity();
        activity.setId(1l);

        TimelineSaveMessage timelineEvent = TimelineSaveMessage.createTimelineEvent(mock(UserEntity.class), proposalMock, activity);

        subscriber.requestApprovalAvaluation(timelineEvent);


        verifyNoMoreInteractions(application, security);

    }
}