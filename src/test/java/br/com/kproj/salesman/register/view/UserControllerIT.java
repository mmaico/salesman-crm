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
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        mockMvc.perform(post("/users/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", "bobstark")
                .param("password", "1234")
                .param("name", "Bob")
                .param("lastname", "Stark")
        ).andExpect(status().isOk())
            .andExpect(view().name("user"));
    }

    @Test
    public void shouldAddUserSavedInContext() throws Exception {

        mockMvc.perform(post("/users/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "bobstark")
                        .param("password", "1234")
                        .param("name", "Bob")
                        .param("lastname", "Stark")
        ).andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidName() throws Exception {

        mockMvc.perform(post("/users/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("login", "bobstark")
                        .param("password", "1234")
                        .param("lastname", "Stark")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldListUserRegistered() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/users/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("user"));
    }



}