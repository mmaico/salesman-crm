package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.BusinessProposalApprovalActivity;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUser;
import br.com.kproj.salesman.infrastructure.security.authentication.LoggedUserBuilder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BusinessProposalTimelineControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BusinessProposalTimelineController controller;

    private UserEntity user;

    private SecurityHelper security;

//    @Before
//    public void setUp() {
//        user = UserEntityBuilder.createUser(2l).build();
//        LoggedUser loggedUser = LoggedUserBuilder.createLoggedUser("admin", user, Sets.newHashSet()).build();
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        this.security = mock(SecurityHelper.class);
//
//        given(this.security.getPrincipal()).willReturn(loggedUser);
//        ReflectionTestUtils.setField(controller, "security", security);
//    }

    @Test
    public void shouldSaveTimelineActivityByBusinessProposal() throws Exception {

        mockMvc.perform(post("/business-proposal/1/logactivity/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("logActivity.type", "EMAIL")
                .param("logActivity.description", "descricao da atividade")
        ).andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/business-proposal/1/activities").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();

        Map<String, Object> model = mvcResult.getModelAndView().getModel();
        Timeline timeline = (Timeline) model.get("timeline");
        BusinessProposalEntity proposal = (BusinessProposalEntity)model.get("businessProposal");


        assertThat(timeline.getActivities().size(), is(1));
        assertThat(timeline.getActivities().get(0).getUser(), is(user));
        assertThat(timeline.getActivities().get(0).getDescription(), is("descricao da atividade"));

        assertThat(proposal.getId(), is(1l));
    }

    @Test
    public void shouldSaveTimelineActivityProposalActivity() throws Exception {

        mockMvc.perform(post("/business-proposal/2/approval-activity/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("logActivity.avaluation", "APPROVED")
                .param("logActivity.description", "descricao da atividade 01")
        ).andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/business-proposal/2/activities").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();

        Map<String, Object> model = mvcResult.getModelAndView().getModel();
        Timeline timeline = (Timeline) model.get("timeline");
        BusinessProposalEntity proposal = (BusinessProposalEntity)model.get("businessProposal");

        BusinessProposalApprovalActivity timelineActivity =  (BusinessProposalApprovalActivity)timeline.getActivities().get(0);
        assertThat(timeline.getActivities().size(), is(1));
        assertThat(timelineActivity.getDescription(), is("descricao da atividade 01"));
        assertThat(timelineActivity.getUser(), is(user));
        assertThat(timelineActivity.getAvaluation(), is(ApproverStatus.APPROVED));

        assertThat(proposal.getId(), is(2l));
    }

}