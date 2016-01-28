package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.ApproverStatus;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
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
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ContactTimelineControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactTimelineController controller;

    private User user;

    private SecurityHelper security;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.security = mock(SecurityHelper.class);

        user = UserBuilder.createUser(2l).build();
        LoggedUser loggedUser = LoggedUserBuilder.createLoggedUser("admin", user, Sets.newHashSet()).build();

        given(this.security.getPrincipal()).willReturn(loggedUser);
        ReflectionTestUtils.setField(controller, "security", security);
    }

    @Test
    public void shouldSaveTimelineActivityByContact() throws Exception {

        mockMvc.perform(post("/contact/1/logactivity/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("logActivity.type", "EMAIL")
                .param("logActivity.description", "descricao da atividade")
        ).andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/contact/1/activities").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();

        Map<String, Object> model = mvcResult.getModelAndView().getModel();
        Timeline timeline = (Timeline) model.get("timeline");
        Contact contact = (Contact)model.get("contact");


        assertThat(timeline.getActivities().size(), is(1));
        assertThat(timeline.getActivities().get(0).getUser(), is(user));
        assertThat(timeline.getActivities().get(0).getDescription(), is("descricao da atividade"));

        assertThat(contact.getId(), is(1l));
    }

}