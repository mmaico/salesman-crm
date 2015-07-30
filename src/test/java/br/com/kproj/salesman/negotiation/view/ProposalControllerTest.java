package br.com.kproj.salesman.negotiation.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test to {@link br.com.kproj.salesman.negotiation.view.ProposalController}
 */
public class ProposalControllerTest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }

    @Test
    public void shouldSaveABusinessProposal() throws Exception {

        mockMvc.perform(post("/negotiations/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("person.id", "1")
                        .param("vendor.id", "2")
                        .param("careOf", "Jose Luiz")
                        .param("deliveryForeCast", "10/02/2018")
                        .param("productItems[0].product.id", "1")
                        .param("productItems[0].price", "300.0")
                        .param("productItems[0].quantity", "2")
                        .param("productItems[1].product.id", "2")
                        .param("productItems[1].price", "300.0")
                        .param("productItems[1].quantity", "1")
                        .param("paymentItems[0].dateDue", "10/02/2017")
                        .param("paymentItems[0].value", "900.00")
        ).andExpect(status().isOk())
                .andExpect(view().name("negotiation"));
    }

    @Test
    public void shouldReturnErrorWhenInvalidVendor() throws Exception {

        mockMvc.perform(post("/negotiations/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("person.id", "1")
                        .param("careOf", "Jose Luiz")
                        .param("deliveryForeCast", "10/02/2018")
                        .param("productItems[0].product.id", "1")
                        .param("productItems[0].price", "300.0")
                        .param("productItems[0].quantity", "2")
                        .param("productItems[1].product.id", "2")
                        .param("productItems[1].price", "300.0")
                        .param("productItems[1].quantity", "1")
                        .param("paymentItems[0].dateDue", "10/02/2017")
                        .param("paymentItems[0].value", "50.0")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenProductItemNotHaveDateDue() throws Exception {

        mockMvc.perform(post("/negotiations/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("vendor.id", "2")
                        .param("person.id", "1")
                        .param("careOf", "Jose Luiz")
                        .param("deliveryForeCast", "10/02/2018")
                        .param("productItems[0].product.id", "1")
                        .param("productItems[0].price", "300.0")
                        .param("productItems[0].quantity", "2")
                        .param("productItems[1].product.id", "2")
                        .param("productItems[1].price", "300.0")
                        .param("productItems[1].quantity", "1")
                        .param("paymentItems[0].value", "50.0")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }

}