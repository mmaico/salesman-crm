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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test to {@link UserController}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSaveUser() throws Exception {

        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", "userLogin")
                .param("password", "pass123")
                .param("name", "Bob")
                .param("lastname", "Stark")
        ).andExpect(status().isOk())
            .andExpect(view().name("user"));
    }

    @Test
    public void shouldAddUserSavedInContext() throws Exception {

        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "userLogin")
                        .param("password", "pass123")
                        .param("name", "Bob")
                        .param("lastname", "Stark")
        ).andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidLogin() throws Exception {

        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("password", "pass123")
                        .param("name", "Bob")
                        .param("lastname", "Stark")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidPassword() throws Exception {

        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "boblogin")
                        .param("name", "Bob")
                        .param("lastname", "Stark")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidName() throws Exception {

        mockMvc.perform(post("/user/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "boblogin")
                        .param("password", "pass123")
                        .param("lastname", "Stark")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }



}