package br.com.kproj.salesman.register.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.enums.PersonProfilesEnum;

/**
 * Test to {@link br.com.kproj.salesman.register.view.ProviderController}
 */

public class ProviderControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    
    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        
    }

    @Test
    public void shouldSaveProvider() throws Exception {

        mockMvc.perform(post("/providers/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "company")
                .param("name", "test name")
                .param("profile.id", PersonProfilesEnum.COMPANY_PROVIDER.get().getId().toString())
                .param("company.tradingName", "nome fantasia")
        ).andExpect(status().isOk())
            .andExpect(view().name("provider"));
    }

    @Test
    public void shouldSaveAndAddProviderInContext() throws Exception {

        mockMvc.perform(post("/providers/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "test name")
                        .param("company.tradingName", "nome fantasia")
                        .param("profile.id", PersonProfilesEnum.COMPANY_PROVIDER.get().getId().toString())
        ).andExpect(model().attributeExists("provider"));
        
    }

    @Test
    public void shouldReturnErrorWhenProviderNotHaveName() throws Exception {

        mockMvc.perform(post("/providers/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("tradingName", "nome fantasia")
                        .param("profile.id", PersonProfilesEnum.COMPANY_PROVIDER.get().getId().toString())
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenProviderNameLessThan2Characters() throws Exception {

        mockMvc.perform(post("/providers/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "t")
                        .param("tradingName", "nome fantasia")
                        .param("profile.id", PersonProfilesEnum.COMPANY_PROVIDER.get().getId().toString())
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenProviderTradingnameGreaterThan30Characters() throws Exception {

        mockMvc.perform(post("/providers/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "company")
                        .param("name", "Client Name")
                        .param("company.tradingName", "qwertyghg10GGGGGGGGG20FFFFFFFFF30GGGGG35")
                        .param("profile.id", PersonProfilesEnum.COMPANY_PROVIDER.get().getId().toString())
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }


    @Test
    public void shouldListProviderRegisted() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/providers/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("provider"));
    }




}