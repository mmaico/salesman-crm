package br.com.kproj.salesman.register.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;

/**
 * Test to {@link ProductController}
 */
public class ProductControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSaveProduct() throws Exception {

        mockMvc.perform(post("/products/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "bobstark")
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("price", "56.45")
                .param("priceCost", "30.10")
        ).andExpect(status().isOk())
            .andExpect(view().name("product"));
    }
    
    @Test
    public void shouldNotSaveWhenProductActiveAndWithoutPrice() throws Exception {

        mockMvc.perform(post("/products/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "bobstark")
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("priceCost", "30.10")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldNotSaveWhenProductWithoutName() throws Exception {

        mockMvc.perform(post("/products/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("price", "56.45")
                .param("priceCost", "30.10")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldListProductsRegistered() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/products/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("product"));
    }



}