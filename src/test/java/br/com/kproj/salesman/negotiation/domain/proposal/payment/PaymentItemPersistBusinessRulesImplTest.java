package br.com.kproj.salesman.negotiation.domain.proposal.payment;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class PaymentItemPersistBusinessRulesImplTest {

	@InjectMocks
	private PaymentItemPersistBusinessRulesImpl rules;
	
	
	@Test
	public void shouldValidateProposalPaymentItem() {
		BusinessProposal businessProposal = new BusinessProposal();
		List<ProposalSaleableItem> products = getProducts();
		businessProposal.setSaleableItems(products);
		
		ProposalPaymentItem installmentOne = new ProposalPaymentItem();
		installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentThree = new ProposalPaymentItem();
		installmentThree.setValue(new BigDecimal("40"));
		
		businessProposal.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentThree));
		 
		
		
		 Boolean result = rules.verifyRules(businessProposal);
		
		
		assertThat(result, is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenInvalidPayment() {
		BusinessProposal businessProposal = new BusinessProposal();
		businessProposal.setSaleableItems(getProducts());
		businessProposal.setPaymentItems(null);
		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(2));
		assertThat(erros.contains("proposal.verify.total.payment.is.diferent.from.total.products"), is(Boolean.TRUE));
		assertThat(erros.contains("proposal.verify.payment.invalid.payment"), is(Boolean.TRUE));
	}

	@Test
	public void shouldReturnErrorWhenIfPaymentIsEmptyAndTotalIsNotZero() {
		BusinessProposal businessProposal = new BusinessProposal();
		List<ProposalSaleableItem> products = getProducts();
		products.get(0).setPrice(BigDecimal.ZERO);
		products.get(1).setPrice(BigDecimal.ZERO);
		businessProposal.setSaleableItems(products);
		
		ProposalPaymentItem payment = new ProposalPaymentItem();
		payment.setValue(BigDecimal.TEN);
		
		businessProposal.setPaymentItems(Lists.newArrayList(payment));
		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(2));
		assertThat(erros.contains("proposal.verify.total.payment.is.diferent.from.total.products"), is(Boolean.TRUE));
		assertThat(erros.contains("proposal.verify.payment.invalid.total"), is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenTotalPaymentIsDiferentFromTotalProducts() {
		BusinessProposal businessProposal = new BusinessProposal();
		List<ProposalSaleableItem> products = getProducts();
		businessProposal.setSaleableItems(products);
		
		ProposalPaymentItem installmentOne = new ProposalPaymentItem();
		installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTree = new ProposalPaymentItem();
		installmentTree.setValue(BigDecimal.ZERO);
		
		businessProposal.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentTree));
		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposal);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(2));
		assertThat(erros.contains("proposal.verify.payment.has.item.with.value.zero"), is(Boolean.TRUE));
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
