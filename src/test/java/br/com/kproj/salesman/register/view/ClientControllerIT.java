package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.Application;
import br.com.kproj.salesman.infra.DBUnitUtil;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test to {@link br.com.kproj.salesman.register.view.ClientController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ClientControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DBUnitUtil dbUnitUtil;

    @BeforeClass
    public static void before() throws DatabaseUnitException, SQLException, IOException {

    }
    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        dbUnitUtil.initDBUnit();

    }

    @Test
    public void shouldSaveClient() throws Exception {

        mockMvc.perform(post("/clients/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "company")
                .param("name", "test name")
                .param("profile.id", "1")
                .param("company.tradingName", "nome fantasia")
        ).andExpect(status().isOk())
            .andExpect(view().name("client"));
    }

    @Test
    public void shouldSaveAndAddClientInContext() throws Exception {

        mockMvc.perform(post("/clients/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "test name")
                        .param("company.tradingName", "nome fantasia")
                        .param("profile.id", "2")
        ).andExpect(model().attributeExists("client"));
    }

    @Test
    public void shouldReturnErrorWhenClientNotHaveName() throws Exception {

        mockMvc.perform(post("/clients/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("tradingName", "nome fantasia")
                        .param("profile.id", "1")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenClientNameLessThan2Characters() throws Exception {

        mockMvc.perform(post("/clients/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "t")
                        .param("tradingName", "nome fantasia")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenClientTradingnameGreaterThan30Characters() throws Exception {

        mockMvc.perform(post("/clients/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "Client Name")
                        .param("company.tradingName", "qwertyghg10GGGGGGGGG20FFFFFFFFF30GGGGG35")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }


    @Test
    public void shouldListClientRegisted() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/clients/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("client"));
    }




}