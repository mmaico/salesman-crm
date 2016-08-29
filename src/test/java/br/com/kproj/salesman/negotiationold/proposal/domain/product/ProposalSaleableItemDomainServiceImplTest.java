package br.com.kproj.salesman.negotiationold.proposal.domain.product;

import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.ProposalSaleableItem;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.negotiationold.proposal.domain.saleable.SaleableItemPersistBusinessRulesImpl;
import br.com.kproj.salesman.negotiationold.proposal.domain.saleable.contract.SaleablePersistBusinessRules;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProposalSaleableItemDomainServiceImplTest {

    @InjectMocks
    private SaleableItemPersistBusinessRulesImpl service;

    @Mock
    private SaleableUnitRepository productRepository;

    @Mock
    private SaleablePersistBusinessRules saleablePersistBusinessRules;


    @Test(expected = ValidationException.class)
    public void shouldReturnTrueWhenNotHaveInvalidItems() {
        BusinessProposalEntity proposal = new BusinessProposalEntity();
        proposal.setSaleableItems(getItemsStub());

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        service.verifyRules(proposal);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenProductDoesNotExist() {
        BusinessProposalEntity proposal = new BusinessProposalEntity();
        proposal.setSaleableItems(getItemsStub());

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.FALSE);

        service.verifyRules(proposal);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenItemHavePriceLessThanZero() {
        BusinessProposalEntity proposal = new BusinessProposalEntity();
        List<ProposalSaleableItem> itemsStub = getItemsStub();
        itemsStub.get(1).setPrice(new BigDecimal("-12.00"));
        proposal.setSaleableItems(itemsStub);

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        service.verifyRules(proposal);

    }

    @Test(expected = ValidationException.class)
    public void shouldReturnFalseWhenItemHaveQuantityLessThanOne() {
        BusinessProposalEntity proposal = new BusinessProposalEntity();
        List<ProposalSaleableItem> itemsStub = getItemsStub();
        itemsStub.get(1).setQuantity(0);
        proposal.setSaleableItems(itemsStub);

        given(productRepository.exists(1l)).willReturn(Boolean.TRUE);
        given(productRepository.exists(2l)).willReturn(Boolean.TRUE);

        service.verifyRules(proposal);

    }

    public List<ProposalSaleableItem> getItemsStub() {
        ProposalSaleableItem itemOne = new ProposalSaleableItem();
        itemOne.setQuantity(1);
        itemOne.setSaleableUnit(new SaleableUnitEntity(1l));
        itemOne.setPrice(BigDecimal.TEN);

        ProposalSaleableItem itemTwo = new ProposalSaleableItem();
        itemTwo.setQuantity(2);
        itemTwo.setSaleableUnit(new SaleableUnitEntity(2l));
        itemTwo.setPrice(BigDecimal.TEN);

        return Lists.newArrayList(itemOne, itemTwo);
    }
}