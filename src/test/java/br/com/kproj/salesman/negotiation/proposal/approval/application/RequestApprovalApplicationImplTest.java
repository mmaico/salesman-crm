package br.com.kproj.salesman.negotiation.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.ApproverProfileBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.RequestApprovalBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder.createBusinessProposal;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RequestApprovalApplicationImplTest {

    @InjectMocks
    private RequestApprovalApplicationImpl application;

    @Mock
    private RequestApprovalRepository repository;

    @Mock
    private ApproverProfileRepository profileRepository;

    @Mock
    private EventBus eventBus;

    @Test
    public void shouldRegisterRequestApproval() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userMock = Mockito.mock(User.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.TRUE);
        given(profileRepository.findAll(Mockito.any(Pageable.class))).willReturn(pageProfilesMock);
        given(pageProfilesMock.getContent()).willReturn(getApproverProfilesStub());
        given(repository.save(requestApproval)).willReturn(requestApproval);

        Optional<RequestApproval> result = application.register(requestApproval);
        assertThat(result.get(), sameInstance(requestApproval));
    }

    @Test
    public void shouldAddApproversAvailableBeforeSave() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userMock = Mockito.mock(User.class);
        Page pageProfilesMock = Mockito.mock(Page.class);
        List<ApproverProfile> approverProfiles = getApproverProfilesStub();

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.TRUE);
        given(profileRepository.findAll(Mockito.any(Pageable.class))).willReturn(pageProfilesMock);
        given(pageProfilesMock.getContent()).willReturn(approverProfiles);
        given(repository.save(requestApproval)).willReturn(requestApproval);

        Optional<RequestApproval> result = application.register(requestApproval);
        List<Approver> approvers = result.get().getApprovers();

        assertThat(approvers.size(), is(2));
        assertThat(approvers.get(0).getApprover(), is(approverProfiles.get(0).getApprover()));
        assertThat(approvers.get(0).getStatus(), is(ApproverStatus.WAITING));
        assertThat(approvers.get(0).getRequestApproval(), sameInstance(requestApproval));

        assertThat(approvers.get(1).getApprover(), is(approverProfiles.get(1).getApprover()));
        assertThat(approvers.get(1).getStatus(), is(ApproverStatus.WAITING));
        assertThat(approvers.get(1).getRequestApproval(), sameInstance(requestApproval));

    }


    @Test
    public void shouldDoNothingWhenNotHaveApprovers() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userMock = Mockito.mock(User.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.FALSE);

        Optional<RequestApproval> result = application.register(requestApproval);

        assertThat(result.isPresent(), is(Boolean.FALSE));


        verify(profileRepository, times(0)).findAll(Mockito.any(Pageable.class));
        verify(pageProfilesMock, times(0)).getContent();
        verify(repository, times(0)).save(Mockito.any(RequestApproval.class));

    }

    @Test
    public void shouldReturnWhenAlreadyExistAnRequestApproval() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userMock = Mockito.mock(User.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApproval));

        Optional<RequestApproval> result = application.register(requestApproval);


        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get(), sameInstance(requestApproval));

        verify(profileRepository, times(0)).hasApprovers();
        verify(profileRepository, times(0)).findAll(Mockito.any(Pageable.class));
        verify(pageProfilesMock, times(0)).getContent();
        verify(repository, times(0)).save(Mockito.any(RequestApproval.class));

    }

    @Test
    public void shouldAddAvaluationApprover() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userStub = UserBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApproval));

        application.evaluationApprover(proposal, userStub, ApproverStatus.APPROVED);

        Approver approver = approversStub.get(2);

        assertThat(approver.getStatus(), is(ApproverStatus.APPROVED));
        assertThat(requestApproval.getStatus(), is(RequestApproval.RequestApprovalStatus.WAITING));

    }

    @Test
    public void shouldUpdateStatusRequestApprovalIfUserDisaproved() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userStub = UserBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApproval));

        application.evaluationApprover(proposal, userStub, ApproverStatus.DISAPPROVED);


        assertThat(requestApproval.getStatus(), is(RequestApproval.RequestApprovalStatus.DISAPPROVED));

    }

    @Test
    public void shouldUpdateStatusRequestApprovalToApprovedWhenAllUserApproved() {
        BusinessProposal proposal = createBusinessProposal(1l).build();
        User userStub = UserBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();
        approversStub.get(0).setStatus(ApproverStatus.APPROVED);

        RequestApproval requestApproval = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApproval.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApproval));

        application.evaluationApprover(proposal, userStub, ApproverStatus.APPROVED);


        assertThat(requestApproval.getStatus(), is(RequestApproval.RequestApprovalStatus.APPROVED));

    }


    private List<Approver> getApproversStub() {
        Approver approver = new Approver();
        approver.setApprover(UserBuilder.createUser(1l).build());
        approver.setDescription("desc 01");
        approver.setId(1l);
        approver.setStatus(ApproverStatus.WAITING);

        Approver approver02 = new Approver();
        approver02.setApprover(UserBuilder.createUser(2l).build());
        approver02.setDescription("desc 02");
        approver02.setId(2l);
        approver02.setStatus(ApproverStatus.APPROVED);


        Approver approver03 = new Approver();
        approver03.setApprover(UserBuilder.createUser(3l).build());
        approver03.setDescription("desc 03");
        approver03.setId(3l);
        approver03.setStatus(ApproverStatus.WAITING);

        return Lists.newArrayList(approver, approver02, approver03);

    }

    private List<ApproverProfile> getApproverProfilesStub() {
        ApproverProfile profileOneActive = ApproverProfileBuilder.createProfile(1l)
                .withApprover(Mockito.mock(User.class))
                .withStatusAvaliable(Boolean.TRUE).build();

        ApproverProfile profileTwoActive = ApproverProfileBuilder.createProfile(2l)
                .withApprover(Mockito.mock(User.class))
                .withStatusAvaliable(Boolean.TRUE).build();

        ApproverProfile profileTwoDisabled = ApproverProfileBuilder.createProfile(3l)
                .withApprover(Mockito.mock(User.class))
                .withStatusAvaliable(Boolean.FALSE).build();

        return Lists.newArrayList(profileOneActive, profileTwoActive, profileTwoDisabled);
    }
}