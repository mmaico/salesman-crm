package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ContactTimelineControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactTimelineController controller;

    private UserEntity user;

    private SecurityHelper security;

//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        this.security = mock(SecurityHelper.class);
//
//        user = UserEntityBuilder.createUser(2l).build();
//        LoggedUser loggedUser = LoggedUserBuilder.createLoggedUser("admin", user, Sets.newHashSet()).build();
//
//        given(this.security.getPrincipal()).willReturn(loggedUser);
//        ReflectionTestUtils.setField(controller, "security", security);
//    }

    @Test
    public void shouldSaveTimelineActivityByContact() throws Exception {

        mockMvc.perform(post("/contact/1/logactivity/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("logActivity.type", "EMAIL")
                .param("logActivity.description", "descricao da atividade")
        ).andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(get("/contact/1/activities").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();

        Map<String, Object> model = mvcResult.getModelAndView().getModel();
        Timeline timeline = (Timeline) model.get("timeline");
        ContactEntity contact = (ContactEntity)model.get("contact");


        assertThat(timeline.getActivities().size(), is(1));
        assertThat(timeline.getActivities().get(0).getUser(), is(user));
        assertThat(timeline.getActivities().get(0).getDescription(), is("descricao da atividade"));

        assertThat(contact.getId(), is(1l));
    }

}