package br.com.kproj.salesman.negotiation.domain.proposal.payment;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PaymentItemPersistBusinessRulesImplTest {

	@InjectMocks
	private PaymentItemPersistBusinessRulesImpl rules;
	
	
	@Test
	public void shouldValidateProposalPaymentItem() {
		BusinessProposal businessProposal = Mockito.spy(new BusinessProposal());
		List<ProposalSaleableItem> products = getProducts();
		businessProposal.setSaleableItems(products);
		
		ProposalPaymentItem installmentOne = new ProposalPaymentItem();
        installmentOne.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
        installmentTwo.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentThree = new ProposalPaymentItem();
        installmentThree.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentThree.setValue(new BigDecimal("40"));
		
		businessProposal.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentThree));

		doReturn(BigDecimal.TEN).when(businessProposal).getTotal();
		doReturn(BigDecimal.TEN).when(businessProposal).getTotalToPay();
		
		
		 Boolean result = rules.verifyRules(businessProposal);
		
		assertThat(result, is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenInvalidPayment() {
		BusinessProposal businessProposal = Mockito.spy(new BusinessProposal());
		businessProposal.setSaleableItems(getProducts());
		businessProposal.setPaymentItems(null);
		Set<String> erros = null;

		doReturn(BigDecimal.TEN).when(businessProposal).getTotal();
		doReturn(BigDecimal.TEN).when(businessProposal).getTotalToPay();
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(2));

        assertThat(erros.contains("proposal.verify.payment.has.invalid.due.date"), is(Boolean.TRUE));
        assertThat(erros.contains("proposal.no.payment.to.sale.with.total.greater.zero"), is(Boolean.TRUE));

	}

	@Test
	public void shouldReturnErrorWhenPaymentIsEmptyAndTotalIsNotZero() {
		BusinessProposal businessProposal = Mockito.spy(new BusinessProposal());
		List<ProposalSaleableItem> products = getProducts();
		products.get(0).setPrice(BigDecimal.TEN);
		products.get(1).setPrice(BigDecimal.ZERO);
		businessProposal.setSaleableItems(products);

		doReturn(BigDecimal.TEN).when(businessProposal).getTotal();

		doReturn(BigDecimal.TEN).when(businessProposal).getTotalToPay();

		businessProposal.setPaymentItems(Lists.newArrayList());
		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(1));
		assertThat(erros.contains("proposal.no.payment.to.sale.with.total.greater.zero"), is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenTotalPaymentIsDiferentFromTotalProposalSaleableItems() throws ParseException {
		BusinessProposal businessProposal = Mockito.spy(new BusinessProposal());
		List<ProposalSaleableItem> products = getProducts();
		businessProposal.setSaleableItems(products);

		ProposalPaymentItem installmentOne = new ProposalPaymentItem();

        installmentOne.setDueDate(DateHelper.convertToDate("10/03/2030"));
        installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
        installmentTwo.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTree = new ProposalPaymentItem();
        installmentTree.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTree.setValue(BigDecimal.ZERO);
		
		businessProposal.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentTree));

		doReturn(BigDecimal.TEN).when(businessProposal).getTotal();

		doReturn(BigDecimal.ONE).when(businessProposal).getTotalToPay();

		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(1));
		assertThat(erros.contains("proposal.verify.total.payment.is.diferent.from.total.products"), is(Boolean.TRUE));
	}
	
	
	
	public List<ProposalSaleableItem> getProducts() {
		ProposalSaleableItem productOne = new ProposalSaleableItem();
		productOne.setPrice(BigDecimal.TEN);
		productOne.setQuantity(1);
		
		ProposalSaleableItem productTwo = new ProposalSaleableItem();
		productTwo.setPrice(BigDecimal.TEN);
		productTwo.setQuantity(5);
		
		return Lists.newArrayList(productOne, productTwo);
		
	}
}
