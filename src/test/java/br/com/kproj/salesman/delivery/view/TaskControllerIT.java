package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import org.dbunit.DatabaseUnitException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSaveTaskTemplateOnlyRequiredFields() throws Exception {

        mockMvc.perform(post("/task/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("description", "description")
                        .param("salesOrder.id", "2")
                        .param("signedBy[0].id", "1")
                        .param("deadline", "06/11/2020")
                        .param("notifications[0].deadline", "06/10/2030")

        ).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorWhenInvalidSignedUser() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/task/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("description", "description")
                        .param("salesOrder.id", "2")
                        .param("signedBy[0].id", "9999")
                        .param("deadline", "06/11/2020")
                        .param("notifications[0].deadline", "06/10/2030")

        ).andExpect(status().isBadRequest()).andReturn();


        String result = mvcResult.getResponse().getContentAsString();

        MatcherAssert.assertThat(result, Matchers.is(""));
    }


    @Test
    public void shouldReturnErrorWhenInvalidSalesOrder() throws Exception {

        mockMvc.perform(post("/task/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("description", "description")
                        .param("salesOrder.id", "9999")
                        .param("signedBy[0].id", "1")
                        .param("deadline", "06/11/2020")
                        .param("notifications[0].deadline", "06/10/2030")

        ).andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnErrorWhenInvalidDeadline() throws Exception {

        mockMvc.perform(post("/task/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("description", "description")
                        .param("salesOrder.id", "2")
                        .param("signedBy[0].id", "1")
                        .param("deadline", "06/11/2010")
                        .param("notifications[0].deadline", "06/10/2030")

        ).andExpect(status().isBadRequest());
    }

}