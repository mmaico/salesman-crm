package br.com.kproj.salesman.negotiation.proposal.view.helpers;

import br.com.kproj.salesman.negotiation.proposal.view.dto.PaymentDTO;
import br.com.kproj.salesman.negotiation.proposal.view.dto.PaymentDefinitionDTO;
import org.hamcrest.MatcherAssert;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.core.Is.is;


@RunWith(MockitoJUnitRunner.class)
public class PaymentDetailHelperTest {

    @InjectMocks
    private PaymentDetailHelper paymentDetailHelper;

    @Mock
    private ProposalSaleablesHelper proposalSaleablesHelper;


    @Test
    public void shouldGenerateInstallmentsWithoutScraps() {
        Date today = DateTime.now().toDate();

        PaymentDefinitionDTO paymentDefinitionDTO = new PaymentDefinitionDTO();
        paymentDefinitionDTO.setFirstDateDue(today);
        paymentDefinitionDTO.setInstallments(3);
        paymentDefinitionDTO.setDateIntervalBetweenDates(30);

        BDDMockito.given(proposalSaleablesHelper.getTotalItems()).willReturn(new BigDecimal("100"));

        PaymentDTO paymentDTO = paymentDetailHelper.generate(paymentDefinitionDTO);

        MatcherAssert.assertThat(paymentDTO.getInstallments().get(0).getValue(), is(new BigDecimal("33.33")));
        MatcherAssert.assertThat(paymentDTO.getInstallments().get(1).getValue(), is(new BigDecimal("33.33")));
        MatcherAssert.assertThat(paymentDTO.getInstallments().get(2).getValue(), is(new BigDecimal("33.34")));
    }

    @Test
    public void shouldGenerateInstallmentsWithDueDate() {
        Date today = DateTime.now().toDate();
        Date dateSecondInstallment = new DateTime(today).plusDays(30).toDate();
        Date dateThirdInstallment = new DateTime(today).plusDays(60).toDate();

        PaymentDefinitionDTO paymentDefinitionDTO = new PaymentDefinitionDTO();
        paymentDefinitionDTO.setFirstDateDue(today);
        paymentDefinitionDTO.setInstallments(3);
        paymentDefinitionDTO.setDateIntervalBetweenDates(30);

        BDDMockito.given(proposalSaleablesHelper.getTotalItems()).willReturn(new BigDecimal("100"));

        PaymentDTO paymentDTO = paymentDetailHelper.generate(paymentDefinitionDTO);

        MatcherAssert.assertThat(paymentDTO.getInstallments().get(0).getDueDate(), is(today));
        MatcherAssert.assertThat(paymentDTO.getInstallments().get(1).getDueDate(), is(dateSecondInstallment));
        MatcherAssert.assertThat(paymentDTO.getInstallments().get(2).getDueDate(), is(dateThirdInstallment));
    }
}