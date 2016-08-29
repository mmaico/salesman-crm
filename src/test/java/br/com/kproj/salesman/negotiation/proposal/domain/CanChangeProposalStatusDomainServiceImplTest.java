package br.com.kproj.salesman.negotiation.proposal.domain;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.negotiation.proposal.approval.application.RequestApprovalApplication;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
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
        UserEntity userMock = Mockito.mock(UserEntity.class);
        RequestApprovalEntity requestApprovalEntityMock = Mockito.mock(RequestApprovalEntity.class);
        BusinessProposalEntity bpMock = Mockito.mock(BusinessProposalEntity.class);


        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.TRUE);
        given(requestApprovalEntityMock.getStatus()).willReturn(RequestApprovalEntity.RequestApprovalStatus.APPROVED);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.of(requestApprovalEntityMock));

        Boolean result = service.isValidBusinessRulesFor(bpMock, userMock);

        MatcherAssert.assertThat(result, is(Boolean.TRUE));

    }

    @Test
    public void shouldNotHaveErrosWhenNotHasApprovers() {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalEntity bpMock = Mockito.mock(BusinessProposalEntity.class);
        RequestApprovalEntity requestApprovalEntityMock = Mockito.mock(RequestApprovalEntity.class);

        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.FALSE);
        given(requestApprovalEntityMock.getStatus()).willReturn(RequestApprovalEntity.RequestApprovalStatus.APPROVED);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.of(requestApprovalEntityMock));

        Boolean result = service.isValidBusinessRulesFor(bpMock, userMock);

        MatcherAssert.assertThat(result, is(Boolean.TRUE));

    }

    @Test(expected = ValidationException.class)
    public void shouldHaveErrosWhenHasApproversAndStatusRequestApprovalIsWaiting () {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        RequestApprovalEntity requestApprovalEntityMock = Mockito.mock(RequestApprovalEntity.class);
        BusinessProposalEntity bpMock = Mockito.mock(BusinessProposalEntity.class);


        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.TRUE);
        given(requestApprovalEntityMock.getStatus()).willReturn(RequestApprovalEntity.RequestApprovalStatus.WAITING);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.of(requestApprovalEntityMock));

        service.isValidBusinessRulesFor(bpMock, userMock);

    }

    @Test(expected = ValidationException.class)
    public void shouldHaveErrosWhenHasApproversAndStatusRequestApprovalIsDisapproved () {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        RequestApprovalEntity requestApprovalEntityMock = Mockito.mock(RequestApprovalEntity.class);
        BusinessProposalEntity bpMock = Mockito.mock(BusinessProposalEntity.class);


        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.TRUE);
        given(requestApprovalEntityMock.getStatus()).willReturn(RequestApprovalEntity.RequestApprovalStatus.DISAPPROVED);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.of(requestApprovalEntityMock));

        service.isValidBusinessRulesFor(bpMock, userMock);

    }

    @Test(expected = ValidationException.class)
    public void shouldHaveErrosWhenHasApproversAndNotHaveRequestApproval () {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalEntity bpMock = Mockito.mock(BusinessProposalEntity.class);


        given(profileRepository.hasApproversExcludeParam(userMock)).willReturn(Boolean.TRUE);
        given(requestapproval.findLastRequestApproval(bpMock)).willReturn(Optional.empty());

        service.isValidBusinessRulesFor(bpMock, userMock);
    }
}