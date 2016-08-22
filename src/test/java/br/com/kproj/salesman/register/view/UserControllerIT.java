package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.register.view.users.UserEntityController;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test to {@link UserEntityController}
 */
public class UserControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        mockMvc.perform(put("/users/save")
                        .param("id", "1")
                        .param("name", "Bob")
                        .param("lastname", "Stark")
                        .param("email", "mmaico@gmail.com")
                        .param("position.id", "1")
                        .param("profile.id", "1")
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldSaveUser() throws Exception {

        mockMvc.perform(fileUpload("/users/save")
                .param("login", "bobstark")
                .param("password", "1234")
                .param("passwordConfirm", "1234")
                .param("name", "Bob")
                .param("lastname", "Stark")
                .param("profile.id", "1")
        ).andExpect(status().isOk());
    }


    @Test
    public void shouldReturnErrorsWhenInvalidName() throws Exception {

        mockMvc.perform(fileUpload("/users/save")
                        .param("login", "bobstark")
                        .param("password", "1234")
                        .param("passwordConfirm", "1234")
                        .param("lastname", "Stark")
        ).andExpect(status().isBadRequest()).andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldReturnErrorWhenInvalidAvatar() throws Exception {
    	MockMultipartFile invalidAvatarFile = new MockMultipartFile("avatarFile", "filename.txt", "text/plain", "some xml".getBytes());
    	
        mockMvc.perform(fileUpload("/users/save")
        		.file(invalidAvatarFile)
        		.param("login", "bobstark")
                .param("password", "1234")
                .param("passwordConfirm", "1234")
                .param("name", "Bob")
                .param("lastname", "Stark")
                .param("profile.id", "1")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldSaveUserWithAvatar() throws Exception {
    	MockMultipartFile invalidAvatarFile = new MockMultipartFile("avatarFile", "image.png", "image/png", "image".getBytes());
    	
        mockMvc.perform(fileUpload("/users/save")
        		.file(invalidAvatarFile)
        		.param("login", "bobstark")
                .param("password", "1234")
                .param("passwordConfirm", "1234")
                .param("name", "Bob")
                .param("lastname", "Stark")
                .param("profile.id", "1")
        ).andExpect(status().isOk());
    }
    
    @Test
    public void shouldReturnErrorWhenAvatarSizeGreaterThan1MB() throws Exception {
    	InputStream inputStream = UserControllerIT.class.getResourceAsStream("/big-image.jpg");
    	
    	MockMultipartFile invalidAvatarFile = new MockMultipartFile("avatarFile", "big-image.jpg", "image/jpeg", 
    			IOUtils.toByteArray(inputStream));
    	
        mockMvc.perform(fileUpload("/users/save")
        		.file(invalidAvatarFile)
        		.param("login", "bobstark")
                .param("password", "1234")
                .param("passwordConfirm", "1234")
                .param("name", "Bob")
                .param("lastname", "Stark")
                .param("profile.id", "1")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
   

    @Test
    public void shouldListUserRegistered() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/users/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("/users/list-items"));
    }



}