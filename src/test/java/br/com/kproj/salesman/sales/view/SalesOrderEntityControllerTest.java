package br.com.kproj.salesman.sales.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.timeline.view.TaskTimelineController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SalesOrderEntityControllerTest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TaskTimelineController controller;

    private UserEntity user;

    private SecurityHelper security;

//    @Before
//    public void setUp() {
//        user = UserEntityBuilder.createUser(2l).build();
//        LoggedUser loggedUser = LoggedUserBuilder.createLoggedUser("admin", user, Sets.newHashSet()).build();
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        this.security = mock(SecurityHelper.class);
//
//        given(this.security.getPrincipal()).willReturn(loggedUser);
//        ReflectionTestUtils.setField(controller, "security", security);
//    }

    @Test
    public void shouldFindSalesOrder() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/sales-order/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk()).andReturn();


        Map<String, Object> model = mvcResult.getModelAndView().getModel();
        SalesOrderEntity salesOrderEntity = (SalesOrderEntity) model.get("salesOrder");

        assertThat(salesOrderEntity.getId(), is(1l));

    }
}