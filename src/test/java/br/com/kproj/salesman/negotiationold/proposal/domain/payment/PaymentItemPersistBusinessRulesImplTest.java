package br.com.kproj.salesman.negotiationold.proposal.domain.payment;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalPaymentItem;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.helpers.DateHelper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
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
		BusinessProposalEntity businessProposalEntity = Mockito.spy(new BusinessProposalEntity());
		List<ProposalSaleableItem> products = getProducts();
		businessProposalEntity.setSaleableItems(products);
		
		ProposalPaymentItem installmentOne = new ProposalPaymentItem();
        installmentOne.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
        installmentTwo.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentThree = new ProposalPaymentItem();
        installmentThree.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentThree.setValue(new BigDecimal("40"));
		
		businessProposalEntity.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentThree));

		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotal();
		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotalToPay();
		
		
		 Boolean result = rules.verifyRules(businessProposalEntity);
		
		assertThat(result, is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenInvalidPayment() {
		BusinessProposalEntity businessProposalEntity = Mockito.spy(new BusinessProposalEntity());
		businessProposalEntity.setSaleableItems(getProducts());
		businessProposalEntity.setPaymentItems(null);
		Set<String> erros = null;

		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotal();
		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotalToPay();
		
		try {
			rules.verifyRules(businessProposalEntity);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(2));

        assertThat(erros.contains("proposal.verify.payment.has.invalid.due.date"), is(Boolean.TRUE));
        assertThat(erros.contains("proposal.no.payment.to.sale.with.total.greater.zero"), is(Boolean.TRUE));

	}

	@Test
	public void shouldReturnErrorWhenPaymentIsEmptyAndTotalIsNotZero() {
		BusinessProposalEntity businessProposalEntity = Mockito.spy(new BusinessProposalEntity());
		List<ProposalSaleableItem> products = getProducts();
		products.get(0).setPrice(BigDecimal.TEN);
		products.get(1).setPrice(BigDecimal.ZERO);
		businessProposalEntity.setSaleableItems(products);

		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotal();

		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotalToPay();

		businessProposalEntity.setPaymentItems(Lists.newArrayList());
		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposalEntity);
		} catch (ValidationException e) {
			erros = e.getErrors();
			
		}
		
		assertThat(erros.size(), is(1));
		assertThat(erros.contains("proposal.no.payment.to.sale.with.total.greater.zero"), is(Boolean.TRUE));
	}
	
	@Test
	public void shouldReturnErrorWhenTotalPaymentIsDiferentFromTotalProposalSaleableItems() throws ParseException {
		BusinessProposalEntity businessProposalEntity = Mockito.spy(new BusinessProposalEntity());
		List<ProposalSaleableItem> products = getProducts();
		businessProposalEntity.setSaleableItems(products);

		ProposalPaymentItem installmentOne = new ProposalPaymentItem();

        installmentOne.setDueDate(DateHelper.convertToDate("10/03/2030"));
        installmentOne.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTwo = new ProposalPaymentItem();
        installmentTwo.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTwo.setValue(BigDecimal.TEN);
		
		ProposalPaymentItem installmentTree = new ProposalPaymentItem();
        installmentTree.setDueDate(DateHelper.convertToDate("10/03/2030"));
		installmentTree.setValue(BigDecimal.ZERO);
		
		businessProposalEntity.setPaymentItems(Lists.newArrayList(installmentOne, installmentTwo, installmentTree));

		doReturn(BigDecimal.TEN).when(businessProposalEntity).getTotal();

		doReturn(BigDecimal.ONE).when(businessProposalEntity).getTotalToPay();

		Set<String> erros = null;
		
		try {
			rules.verifyRules(businessProposalEntity);
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
