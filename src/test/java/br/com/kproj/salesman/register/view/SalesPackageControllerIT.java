package br.com.kproj.salesman.register.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test to {@link br.com.kproj.salesman.register.view.saleable.SalesPackageController}
 */
public class SalesPackageControllerIT extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldSavePackage() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "bobstark")
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("price", "56.45")
                .param("priceCost", "30.10")
                .param("saleableUnits[0].id", "2")
        ).andExpect(status().isOk());
    }


    @Test
    public void shouldNotSavePackageWithoutProduct() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "bobstark")
                        .param("description", "descricao do produto")
                        .param("active", "true")
                        .param("price", "56.45")
                        .param("priceCost", "30.10")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldNotSaveWhenPackageWithoutPrice() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "bobstark")
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("priceCost", "30.10")
                .param("saleableUnits[0].id", "2")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldNotSaveWhenPackageWithoutName() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "descricao do produto")
                .param("active", "true")
                .param("price", "56.45")
                .param("priceCost", "30.10")
                .param("saleableUnits[0].id", "2")
        ).andExpect(status().isBadRequest())
            .andExpect(model().attributeExists("errors"));
    }
    
    @Test
    public void shouldListPackagesRegistered() throws Exception {

        ModelAndView modelAndView = mockMvc.perform(get("/sales-package/list")).andExpect(status().isOk())
                .andReturn().getModelAndView();


        assertThat(modelAndView.getViewName(), is("/packages/list-items"));
    }


    @Test
    public void shouldNotSaveWhenPackagePriceLessThanZero() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "bobstark")
                        .param("description", "descricao do produto")
                        .param("active", "true")
                        .param("price", "-10.00")
                        .param("priceCost", "30.10")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldNotSaveWhenPackagePriceCostLessThanZero() throws Exception {

        mockMvc.perform(post("/sales-package/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "bobstark")
                        .param("description", "descricao do produto")
                        .param("active", "true")
                        .param("price", "10.00")
                        .param("priceCost", "-30.10")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }


}