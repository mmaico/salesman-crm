package br.com.kproj.salesman.auditing.application;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.auditing.BusinessProposalAudinting;
import br.com.kproj.salesman.infrastructure.entity.builders.BusinessProposalBuilder;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.events.messages.ProposalAuditingAfterUpdateMessage;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalAuditingRepository;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Gson.class)
public class BusinessProposalEntityAuditingApplicationImplTest {

    @InjectMocks
    private BusinessProposalAuditingApplicationImpl application;

    @Mock
    private BusinessProposalAuditingRepository repository;

    @Mock
    private EventBus eventBus;

    @Captor
    private ArgumentCaptor<BusinessProposalAudinting> argumentCaptor;

    private Gson gson;

    @Before
    public void setUp() {
        gson = PowerMockito.mock(Gson.class);
        ReflectionTestUtils.setField(application, "gson", gson);
    }

    @Test
    public void shouldRegisterFirstBusinessProposal () {
        BusinessProposalEntity businessProposalEntity = BusinessProposalBuilder.createBusinessProposal(1l).build();
        Page pageResultMock = Mockito.mock(Page.class);
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalAudinting audintingMock = Mockito.mock(BusinessProposalAudinting.class);

        given(pageResultMock.getContent()).willReturn(Lists.newArrayList());
        given(repository.findLasVersion(Mockito.eq(1l), Mockito.any(Pageable.class))).willReturn(pageResultMock);
        given(repository.save(Mockito.any(BusinessProposalAudinting.class))).willReturn(audintingMock);

        Optional<BusinessProposalAudinting> result = application.registerAuditing(businessProposalEntity, userMock);

        MatcherAssert.assertThat(result.get(), Matchers.sameInstance(audintingMock));

    }

    @Test
    public void shouldSaveAndBuildCorrectlyAuditing () {
        String json = "{json}";
        BusinessProposalEntity businessProposalEntity = BusinessProposalBuilder.createBusinessProposal(1l).build();
        Page pageResultMock = Mockito.mock(Page.class);
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalAudinting audintingMock = Mockito.mock(BusinessProposalAudinting.class);

        given(pageResultMock.getContent()).willReturn(Lists.newArrayList());
        given(repository.findLasVersion(Mockito.eq(1l), Mockito.any(Pageable.class))).willReturn(pageResultMock);
        given(repository.save(Mockito.any(BusinessProposalAudinting.class))).willReturn(audintingMock);
        given(gson.toJson(businessProposalEntity)).willReturn(json);

        application.registerAuditing(businessProposalEntity, userMock);

        Mockito.verify(repository).save(argumentCaptor.capture());
        BusinessProposalAudinting audintingCaptor = argumentCaptor.getValue();

        MatcherAssert.assertThat(audintingCaptor.getEntityId(), Matchers.is(1l));
        MatcherAssert.assertThat(audintingCaptor.getInfo(), Matchers.is(json));
        MatcherAssert.assertThat(audintingCaptor.getLastUpdate(), Matchers.notNullValue());
        MatcherAssert.assertThat(audintingCaptor.getUser(), Matchers.sameInstance(userMock));

    }

    @Test
    public void shouldSaveAndSendMessage () {
        String json = "{json}";
        String jsonNew = "{jsonNew}";
        BusinessProposalEntity businessProposalEntity = BusinessProposalBuilder.createBusinessProposal(1l).build();
        Page pageResultMock = Mockito.mock(Page.class);
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalAudinting audintingMock = Mockito.mock(BusinessProposalAudinting.class);

        BusinessProposalAudinting audintingMockDB = new BusinessProposalAudinting();
        audintingMockDB.setInfo(json);

        given(pageResultMock.getContent()).willReturn(Lists.newArrayList(audintingMockDB));
        given(repository.findLasVersion(Mockito.eq(1l), Mockito.any(Pageable.class))).willReturn(pageResultMock);
        given(repository.save(Mockito.any(BusinessProposalAudinting.class))).willReturn(audintingMock);
        given(gson.toJson(businessProposalEntity)).willReturn(jsonNew);

        application.registerAuditing(businessProposalEntity, userMock);

        Mockito.verify(eventBus).post(Mockito.any(ProposalAuditingAfterUpdateMessage.class));

    }

    @Test
    public void shouldNotSaveWhenObjectNotModifier () throws IOException {
        String json = "{json}";

        BusinessProposalEntity businessProposalEntity = BusinessProposalBuilder.createBusinessProposal(1l).build();
        Page pageResultMock = Mockito.mock(Page.class);
        UserEntity userMock = Mockito.mock(UserEntity.class);
        BusinessProposalAudinting audintingMock = Mockito.mock(BusinessProposalAudinting.class);

        BusinessProposalAudinting audintingMockDB = new BusinessProposalAudinting();
        audintingMockDB.setInfo(json);

        given(pageResultMock.getContent()).willReturn(Lists.newArrayList(audintingMockDB));
        given(repository.findLasVersion(Mockito.eq(1l), Mockito.any(Pageable.class))).willReturn(pageResultMock);
        given(repository.save(Mockito.any(BusinessProposalAudinting.class))).willReturn(audintingMock);
        given(gson.toJson(businessProposalEntity)).willReturn(json);

        application.registerAuditing(businessProposalEntity, userMock);

        Mockito.verify(repository, Mockito.times(0)).save(Mockito.any(BusinessProposalAudinting.class));

    }


}