package br.com.kproj.salesman.negotiation.domain.proposal;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import br.com.kproj.salesman.negotiation.approval.application.RequestApprovalApplication;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CanChangeProposalStatusDomainServiceImplTest {

    @InjectMocks
    private CanChangeProposalStatusDomainServiceImpl service;

    @Mock
    private ApproverProfileRepository profileRepository;

    @Mock
    private RequestApprovalApplication requestapproval;


    @Test
    public void shouldNotHaveErrosWhenHasApproversAndStatusRequestApprovalIsApproved () {
        User userMock = Mockito.mock(User.class);
        RequestApproval requestApprovalMock = Mockito.mock(RequestApproval.class);
        BusinessProposal bpMock = Mockito.mock(BusinessProposal.class);


        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.TRUE);
        given(requestApprovalMock.getStatus()).willReturn(RequestApproval.RequestApprovalStatus.APPROVED);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.of(requestApprovalMock));

        Boolean result = service.isValidBusinessRulesFor(bpMock, userMock);

        MatcherAssert.assertThat(result, is(Boolean.TRUE));

    }
}