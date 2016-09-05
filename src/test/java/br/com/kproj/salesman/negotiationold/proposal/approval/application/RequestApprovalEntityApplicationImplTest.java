package br.com.kproj.salesman.negotiationold.proposal.approval.application;

import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.ApproverEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.RequestApprovalBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.Approver;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApprovalEntity;
import br.com.kproj.salesman.infrastructure.repository.ApproverProfileRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalEntityRepository;
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
public class RequestApprovalEntityApplicationImplTest {

    @InjectMocks
    private RequestApprovalApplicationImpl application;

    @Mock
    private RequestApprovalEntityRepository repository;

    @Mock
    private ApproverProfileRepository profileRepository;

    @Mock
    private EventBus eventBus;

    @Test
    public void shouldRegisterRequestApproval() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.TRUE);
        given(profileRepository.findAll(Mockito.any(Pageable.class))).willReturn(pageProfilesMock);
        given(pageProfilesMock.getContent()).willReturn(getApproverProfilesStub());
        given(repository.save(requestApprovalEntity)).willReturn(requestApprovalEntity);

        Optional<RequestApprovalEntity> result = application.register(requestApprovalEntity);
        assertThat(result.get(), sameInstance(requestApprovalEntity));
    }

    @Test
    public void shouldAddApproversAvailableBeforeSave() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Page pageProfilesMock = Mockito.mock(Page.class);
        List<ApproverEntity> approverEntities = getApproverProfilesStub();

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.TRUE);
        given(profileRepository.findAll(Mockito.any(Pageable.class))).willReturn(pageProfilesMock);
        given(pageProfilesMock.getContent()).willReturn(approverEntities);
        given(repository.save(requestApprovalEntity)).willReturn(requestApprovalEntity);

        Optional<RequestApprovalEntity> result = application.register(requestApprovalEntity);
        List<Approver> approvers = result.get().getApprovers();

        assertThat(approvers.size(), is(2));
        assertThat(approvers.get(0).getApprover(), is(approverEntities.get(0).getApprover()));
        assertThat(approvers.get(0).getStatus(), is(ApproverStatus.WAITING));
        assertThat(approvers.get(0).getRequestApprovalEntity(), sameInstance(requestApprovalEntity));

        assertThat(approvers.get(1).getApprover(), is(approverEntities.get(1).getApprover()));
        assertThat(approvers.get(1).getStatus(), is(ApproverStatus.WAITING));
        assertThat(approvers.get(1).getRequestApprovalEntity(), sameInstance(requestApprovalEntity));

    }


    @Test
    public void shouldDoNothingWhenNotHaveApprovers() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.empty());
        given(profileRepository.hasApprovers()).willReturn(Boolean.FALSE);

        Optional<RequestApprovalEntity> result = application.register(requestApprovalEntity);

        assertThat(result.isPresent(), is(Boolean.FALSE));


        verify(profileRepository, times(0)).findAll(Mockito.any(Pageable.class));
        verify(pageProfilesMock, times(0)).getContent();
        verify(repository, times(0)).save(Mockito.any(RequestApprovalEntity.class));

    }

    @Test
    public void shouldReturnWhenAlreadyExistAnRequestApproval() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Page pageProfilesMock = Mockito.mock(Page.class);

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withUserRequester(userMock).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApprovalEntity));

        Optional<RequestApprovalEntity> result = application.register(requestApprovalEntity);


        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get(), sameInstance(requestApprovalEntity));

        verify(profileRepository, times(0)).hasApprovers();
        verify(profileRepository, times(0)).findAll(Mockito.any(Pageable.class));
        verify(pageProfilesMock, times(0)).getContent();
        verify(repository, times(0)).save(Mockito.any(RequestApprovalEntity.class));

    }

    @Test
    public void shouldAddAvaluationApprover() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userStub = UserEntityBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApprovalEntity));

        application.evaluationApprover(proposal, userStub, ApproverStatus.APPROVED);

        Approver approver = approversStub.get(2);

        assertThat(approver.getStatus(), is(ApproverStatus.APPROVED));
        assertThat(requestApprovalEntity.getStatus(), is(RequestApprovalEntity.RequestApprovalStatus.WAITING));

    }

    @Test
    public void shouldUpdateStatusRequestApprovalIfUserDisaproved() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userStub = UserEntityBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApprovalEntity));

        application.evaluationApprover(proposal, userStub, ApproverStatus.DISAPPROVED);


        assertThat(requestApprovalEntity.getStatus(), is(RequestApprovalEntity.RequestApprovalStatus.DISAPPROVED));

    }

    @Test
    public void shouldUpdateStatusRequestApprovalToApprovedWhenAllUserApproved() {
        BusinessProposalEntity proposal = createBusinessProposal(1l).build();
        UserEntity userStub = UserEntityBuilder.createUser(3l).build();
        List<Approver> approversStub = getApproversStub();
        approversStub.get(0).setStatus(ApproverStatus.APPROVED);

        RequestApprovalEntity requestApprovalEntity = RequestApprovalBuilder.createRequestApproval()
                .withProposal(proposal).withStatus(RequestApprovalEntity.RequestApprovalStatus.WAITING)
                .withApprovers(approversStub)
                .withUserRequester(userStub).build();

        given(repository.findByProposal(proposal)).willReturn(Optional.of(requestApprovalEntity));

        application.evaluationApprover(proposal, userStub, ApproverStatus.APPROVED);


        assertThat(requestApprovalEntity.getStatus(), is(RequestApprovalEntity.RequestApprovalStatus.APPROVED));

    }


    private List<Approver> getApproversStub() {
        Approver approver = new Approver();
        approver.setApprover(UserEntityBuilder.createUser(1l).build());
        approver.setDescription("desc 01");
        approver.setId(1l);
        approver.setStatus(ApproverStatus.WAITING);

        Approver approver02 = new Approver();
        approver02.setApprover(UserEntityBuilder.createUser(2l).build());
        approver02.setDescription("desc 02");
        approver02.setId(2l);
        approver02.setStatus(ApproverStatus.APPROVED);


        Approver approver03 = new Approver();
        approver03.setApprover(UserEntityBuilder.createUser(3l).build());
        approver03.setDescription("desc 03");
        approver03.setId(3l);
        approver03.setStatus(ApproverStatus.WAITING);

        return Lists.newArrayList(approver, approver02, approver03);

    }

    private List<ApproverEntity> getApproverProfilesStub() {
        ApproverEntity profileOneActive = ApproverEntityBuilder.createProfile(1l)
                .withApprover(Mockito.mock(UserEntity.class))
                .withStatusAvaliable(Boolean.TRUE).build();

        ApproverEntity profileTwoActive = ApproverEntityBuilder.createProfile(2l)
                .withApprover(Mockito.mock(UserEntity.class))
                .withStatusAvaliable(Boolean.TRUE).build();

        ApproverEntity profileTwoDisabled = ApproverEntityBuilder.createProfile(3l)
                .withApprover(Mockito.mock(UserEntity.class))
                .withStatusAvaliable(Boolean.FALSE).build();

        return Lists.newArrayList(profileOneActive, profileTwoActive, profileTwoDisabled);
    }
}