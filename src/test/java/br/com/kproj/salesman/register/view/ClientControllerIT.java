package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.Application;
import org.junit.Before;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSaveClient() throws Exception {

        mockMvc.perform(post("/client/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "test name")
                .param("tradingName", "nome fantasia")
                .param("user.login", "login-test")
                .param("user.password", "123456")
        ).andExpect(status().isOk())
            .andExpect(view().name("client"));
    }

    @Test
    public void shouldSaveAndAddClientInContext() throws Exception {

        mockMvc.perform(post("/client/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "test name")
                        .param("tradingName", "nome fantasia")
                        .param("user.login", "login-test")
                        .param("user.password", "123456")
        ).andExpect(model().attributeExists("client"));
    }

    @Test
    public void shouldReturnErrorWhenClientNotHaveName() throws Exception {

        mockMvc.perform(post("/client/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("tradingName", "nome fantasia")
                        .param("user.login", "login-test")
                        .param("user.password", "123456")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenClientNameLessThan2Characters() throws Exception {

        mockMvc.perform(post("/client/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "t")
                        .param("tradingName", "nome fantasia")
                        .param("user.login", "login-test")
                        .param("user.password", "123456")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenClientTradingnameGreaterThan30Characters() throws Exception {

        mockMvc.perform(post("/client/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Client Name")
                        .param("tradingName", "qwertyghg10GGGGGGGGG20FFFFFFFFF30GGGGG35")
                        .param("user.login", "login-test")
                        .param("user.password", "123456")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }
}