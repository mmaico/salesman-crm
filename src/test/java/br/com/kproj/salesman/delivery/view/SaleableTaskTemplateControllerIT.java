package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import org.dbunit.DatabaseUnitException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ActiveProfiles("test")
public class SaleableTaskTemplateControllerIT extends AbstractIntegrationTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSaveTaskTemplateOnlyRequiredFields() throws Exception {

        mockMvc.perform(post("/saleables/1/task-template/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("region.id", "2")
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldNotSaveWhenNotHaveTitle() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("/saleables/1/task-template/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("region.id", "2")
        ).andExpect(status().isBadRequest()).andReturn().getResponse();

        String result = response.getContentAsString();

        assertThat(result, is("valor\n"));
    }

    @Test
    public void shouldReturnUrlWithNewId() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("/saleables/1/task-template/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "titulo")
                        .param("region.id", "2")
        ).andExpect(status().isOk()).andReturn().getResponse();

    }


    @Test
    public void shouldListAllTaskTemplates() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/saleables/1/task-template/list")).andExpect(status().isOk()).andReturn().getModelAndView();

        Map<String, Object> model = modelAndView.getModel();


        List<TaskTemplateEntity> taskTemplateEntities =  (List<TaskTemplateEntity>) model.get("taskTemplates");

        assertThat(taskTemplateEntities.size(), Matchers.is(2));

    }

    @Test
    public void shouldGetOneTaskTemplateByID() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/saleables/task-template/1")).andExpect(status().isOk()).andReturn().getModelAndView();

        Map<String, Object> model = modelAndView.getModel();


        TaskTemplateEntity template =  (TaskTemplateEntity) model.get("taskTemplate");


        assertThat(template.getId(), is(1l));
        assertThat(template.getTitle(), is("title"));
    }
}