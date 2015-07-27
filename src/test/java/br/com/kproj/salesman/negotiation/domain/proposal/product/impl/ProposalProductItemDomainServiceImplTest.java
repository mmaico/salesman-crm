package br.com.kproj.salesman.negotiation.domain.proposal.product.impl;

import br.com.kproj.salesman.infrastructure.entity.Product;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposal;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalProductItem;
import br.com.kproj.salesman.infrastructure.repository.ProductRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalProductItemDomainServiceImplTest {

    @InjectMocks
    private ProductItemPersistBusinessRulesImpl service;

    @Mock
    private ProductRepository productRepository;


    @Test
    public void shouldReturnTrueWhenNotHaveInvalidItems() {
        BusinessProposal proposal = new BusinessProposal();
        proposal.setProductItems(getItemsStub());

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        Boolean result = service.verifyRules(proposal);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldReturnFalseWhenProductDoesNotExist() {
        BusinessProposal proposal = new BusinessProposal();
        proposal.setProductItems(getItemsStub());

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.FALSE);

        Boolean result = service.verifyRules(proposal);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnFalseWhenItemHavePriceLessThanZero() {
        BusinessProposal proposal = new BusinessProposal();
        List<ProposalProductItem> itemsStub = getItemsStub();
        itemsStub.get(1).setPrice(new BigDecimal("-12.00"));
        proposal.setProductItems(itemsStub);

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        Boolean result = service.verifyRules(proposal);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldReturnFalseWhenItemHaveQuantityLessThanOne() {
        BusinessProposal proposal = new BusinessProposal();
        List<ProposalProductItem> itemsStub = getItemsStub();
        itemsStub.get(1).setQuantity(0);
        proposal.setProductItems(itemsStub);

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        Boolean result = service.verifyRules(proposal);

        assertThat(result, is(Boolean.FALSE));
    }

    public List<ProposalProductItem> getItemsStub() {
        ProposalProductItem itemOne = new ProposalProductItem();
        itemOne.setQuantity(1);
        itemOne.setProduct(new Product(1l));
        itemOne.setPrice(BigDecimal.TEN);

        ProposalProductItem itemTwo = new ProposalProductItem();
        itemTwo.setQuantity(2);
        itemTwo.setProduct(new Product(2l));
        itemTwo.setPrice(BigDecimal.TEN);

        return Lists.newArrayList(itemOne, itemTwo);
    }
}