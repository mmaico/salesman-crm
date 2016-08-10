package br.com.kproj.salesman.negotiation.proposal.view;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.builders.SaleableUnitBuilder;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BusinessProposalRepository;
import br.com.kproj.salesman.negotiation.proposal.view.dto.UpdateQuantityPriceItemsDTO;
import br.com.kproj.salesman.negotiation.proposal.view.dto.session.ProposalSaleablesDTO;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test to {@link br.com.kproj.salesman.negotiation.proposal.view.BusinessProposalController}
 */
@Component
public class BusinessProposalControllerTest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BusinessProposalRepository repository;

    @Autowired
    private BusinessProposalController controller;

    protected MockHttpSession mockSession;


    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        mockSession = new MockHttpSession(this.webApplicationContext.getServletContext(), UUID.randomUUID().toString());
    }

    @After
    public void after() {
        mockSession.clearAttributes();
    }

    @Test
    public void shouldSaveABusinessProposal() throws Exception {
        ProposalSaleablesDTO dto = createProposalSaleableDTO();
        ReflectionTestUtils.setField(controller, "proposalSaleablesDTO", dto);

        mockMvc.perform(post("/proposals/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .session(mockSession)
                        .param("client.id", "1")
                        .param("seller.id", "1")
                        .param("careOf", "Jose Luiz")
                        .param("deliveryForeCast", "10/02/2018")
                        .param("operationRegion.id", "1")
                        .param("paymentItems[0].dueDate", "10/02/2017")
                        .param("paymentItems[0].value", "850.00")
        ).andExpect(status().isOk());

    }

    @Test
    public void shouldReturnErrorWhenInvalidVendor() throws Exception {

        mockMvc.perform(post("/proposals/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("domain.person.id", "1")
                        .param("domain.careOf", "Jose Luiz")
                        .param("domain.deliveryForeCast", "10/02/2018")
                        .param("domain.operationRegion.id", "1")
                        .param("items[0].saleableUnit.id", "1")
                        .param("items[0].price", "300.00")
                        .param("items[0].quantity", "2")
                        .param("items[1].saleableUnit.id", "2")
                        .param("items[1].price", "300.00")
                        .param("items[1].quantity", "1")
                        .param("domain.paymentItems[0].dueDate", "10/02/2017")
                        .param("domain.paymentItems[0].value", "900.00")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));

    }

    @Test
    public void shouldReturnErrorWhenProductItemNotHaveDueDate() throws Exception {

        mockMvc.perform(post("/proposals/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("domain.person.id", "1")
                        .param("domain.vendor.id", "1")
                        .param("domain.careOf", "Jose Luiz")
                        .param("domain.deliveryForeCast", "10/02/2018")
                        .param("domain.operationRegion.id", "1")
                        .param("items[0].saleableUnit.id", "1")
                        .param("items[0].price", "300.00")
                        .param("items[0].quantity", "2")
                        .param("items[1].saleableUnit.id", "2")
                        .param("items[1].price", "300.00")
                        .param("items[1].quantity", "1")
                        .param("domain.paymentItems[0].dueDate", "10/02/2010")
                        .param("domain.paymentItems[0].value", "900.00")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));
    }

    @Test
    public void shouldReturnErrorWhenAddPaymentWithDiferentValuesBetweenPaymentsAndProducts() throws Exception {

        mockMvc.perform(put("/proposals/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("person.id", "1")
                        .param("vendor.id", "1")
                        .param("careOf", "Jose Luiz")
                        .param("deliveryForeCast", "10/02/2018")
                        .param("operationRegion.id", "1")
                        .param("items[0].saleable.id", "1")
                        .param("items[0].price", "200.0")
                        .param("items[0].quantity", "2")
                        .param("items[1].saleable.id", "2")
                        .param("items[1].price", "300.0")
                        .param("items[1].quantity", "1")
                        .param("paymentItems[0].id", "1")
                        .param("paymentItems[0].dueDate", "10/02/2017")
                        .param("paymentItems[0].value", "700.00")
        ).andExpect(status().isBadRequest())
                .andExpect(model().attributeExists("errors"));

    }

    public ProposalSaleablesDTO createProposalSaleableDTO() {
        SaleableApplication applicationMock = Mockito.mock(SaleableApplication.class);

        ProposalSaleablesDTO dto = new ProposalSaleablesDTO();
        dto.setApplication(applicationMock);

        SaleableUnitEntity saleableUnit = SaleableUnitBuilder.createSaleableUnit(1l).withPrice(BigDecimal.TEN).build();
        SaleableUnitEntity saleableUnit1 = SaleableUnitBuilder.createSaleableUnit(2l).withPrice(BigDecimal.ONE).build();

        given(applicationMock.getOne(1l)).willReturn(Optional.of(saleableUnit));
        given(applicationMock.getOne(2l)).willReturn(Optional.of(saleableUnit1));

        dto.add(saleableUnit);
        dto.add(saleableUnit1);

        UpdateQuantityPriceItemsDTO updateItemOne = new UpdateQuantityPriceItemsDTO();
        updateItemOne.setPrice(new BigDecimal("300"));
        updateItemOne.setQuantity(2);
        updateItemOne.setSaleableId(1l);

        UpdateQuantityPriceItemsDTO updateItemTwo = new UpdateQuantityPriceItemsDTO();
        updateItemTwo.setPrice(new BigDecimal("250"));
        updateItemTwo.setQuantity(1);
        updateItemTwo.setSaleableId(2l);

        dto.updateRootItem(updateItemOne);
        dto.updateRootItem(updateItemTwo);

        return dto;
    }



}