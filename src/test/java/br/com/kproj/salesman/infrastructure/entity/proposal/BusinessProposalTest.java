package br.com.kproj.salesman.infrastructure.entity.proposal;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class BusinessProposalTest {

    @InjectMocks
    private BusinessProposal businessProposal;


    @Test
    public void shouldReturnTotal() {

        List<ProposalSaleableItem> productItems = getProductItems();
        businessProposal.setSaleableItems(productItems);

        BigDecimal total = businessProposal.getTotal();

        assertThat(total, is(new BigDecimal(25)));
    }

    @Test
    public void shouldReturnTotalToPay() {

        List<ProposalPaymentItem> paymentItems = getPaymentItems();
        businessProposal.setPaymentItems(paymentItems);

        BigDecimal total = businessProposal.getTotalToPay();

        assertThat(total, is(new BigDecimal(11)));
    }

    
    private List<ProposalSaleableItem> getProductItems() {
        ProposalSaleableItem itemOne = new ProposalSaleableItem();
        itemOne.setPrice(BigDecimal.TEN);
        itemOne.setQuantity(2);
        ProposalSaleableItem itemTwo = new ProposalSaleableItem();
        itemTwo.setPrice(BigDecimal.ONE);
        itemTwo.setQuantity(5);

        return Lists.newArrayList(itemOne, itemTwo);
    }
    
    private List<ProposalPaymentItem> getPaymentItems() {
    	ProposalPaymentItem itemOne = new ProposalPaymentItem();
        itemOne.setValue(BigDecimal.TEN);
        
        ProposalPaymentItem itemTwo = new ProposalPaymentItem();
        itemTwo.setValue(BigDecimal.ONE);
        
        return Lists.newArrayList(itemOne, itemTwo);
    }


}